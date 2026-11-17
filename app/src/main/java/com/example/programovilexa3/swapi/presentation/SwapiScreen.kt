package com.example.programovilexa3.swapi.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.programovilexa3.swapi.domain.model.SwapiCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun SwapiScreen(viewModel: SwapiViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    BackHandler(enabled = state.selectedId != null) { viewModel.backToList() }

    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Star Wars API", style = MaterialTheme.typography.headlineMedium)
            if (state.selectedId == null) {
                Text("Selecciona un personaje para ver sus datos", style = MaterialTheme.typography.bodyMedium)
                if (state.loadingList && state.characters.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else if (state.characters.isEmpty() && state.listError != null) {
                    Text(state.listError!!, color = MaterialTheme.colorScheme.error)
                    Button(onClick = viewModel::loadPeople) { Text("Reintentar") }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(state.characters, key = { it.id }) { item ->
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth().clickable {
                                    viewModel.selectCharacter(item.id)
                                }
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text(item.name, style = MaterialTheme.typography.titleMedium)
                                    Text("Ver información", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                        if (state.listError != null) {
                            item {
                                Text(state.listError!!, color = MaterialTheme.colorScheme.error)
                                Button(onClick = viewModel::loadPeople) { Text("Reintentar") }
                            }
                        } else if (state.hasNext) {
                            item {
                                if (state.loadingMore) {
                                    CircularProgressIndicator()
                                } else {
                                    Button(onClick = viewModel::loadPeople) { Text("Cargar más personajes") }
                                }
                            }
                        }
                    }
                }
            } else {
                TextButton(onClick = viewModel::backToList) { Text("← Volver a personajes") }
                when {
                    state.loadingDetails -> {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    state.detailError != null -> {
                        Text(state.detailError!!, color = MaterialTheme.colorScheme.error)
                        Button(onClick = viewModel::retryDetails) { Text("Reintentar") }
                    }
                    state.selectedCharacter != null -> {
                        Column(Modifier.verticalScroll(rememberScrollState())) {
                            CharacterCard(state.selectedCharacter!!)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterCard(character: SwapiCharacter) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(character.name, style = MaterialTheme.typography.titleLarge)
            CharacterField("Altura", character.height.withUnit("cm"))
            CharacterField("Peso", character.mass.withUnit("kg"))
            CharacterField("Color de cabello", character.hairColor)
            CharacterField("Color de piel", character.skinColor)
            CharacterField("Color de ojos", character.eyeColor)
            CharacterField("Género", character.gender)
        }
    }
}

private fun String.withUnit(unit: String): String =
    if (lowercase() in setOf("unknown", "n/a", "none")) this else "$this $unit"

@Composable
private fun CharacterField(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}
