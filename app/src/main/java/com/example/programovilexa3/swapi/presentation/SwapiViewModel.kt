package com.example.programovilexa3.swapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SwapiViewModel(
    private val getCharacter: GetSwapiCharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SwapiUiState>(SwapiUiState.Loading)
    val state: StateFlow<SwapiUiState> = _state

    init { loadCharacter() }

    fun loadCharacter() {
        viewModelScope.launch {
            _state.value = SwapiUiState.Loading
            getCharacter(1).fold(
                onSuccess = { _state.value = SwapiUiState.Success(it) },
                onFailure = {
                    _state.value = SwapiUiState.Error(
                        "No se pudo cargar el personaje. Revisa tu conexión y reintenta."
                    )
                }
            )
        }
    }
}
