package com.example.programovilexa3.swapi.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text("Star Wars API", style = MaterialTheme.typography.headlineMedium)
            Text("Ejercicio 1 · Personaje #1", style = MaterialTheme.typography.bodyMedium)
            when (val current = state) {
                SwapiUiState.Loading -> {
                    Spacer(Modifier.height(30.dp))
                    CircularProgressIndicator()
                    Text("Cargando a Luke Skywalker...")
                }
                is SwapiUiState.Error -> {
                    Text(current.message, color = MaterialTheme.colorScheme.error)
                    Button(onClick = viewModel::loadCharacter) { Text("Reintentar") }
                }
                is SwapiUiState.Success -> CharacterCard(current.character)
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
            CharacterField("Altura", "${character.height} cm")
            CharacterField("Peso", "${character.mass} kg")
            CharacterField("Color de cabello", character.hairColor)
            CharacterField("Color de piel", character.skinColor)
            CharacterField("Color de ojos", character.eyeColor)
            CharacterField("Género", character.gender)
        }
    }
}

@Composable
private fun CharacterField(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}
