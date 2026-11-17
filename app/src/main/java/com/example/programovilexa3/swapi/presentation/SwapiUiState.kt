package com.example.programovilexa3.swapi.presentation

import com.example.programovilexa3.swapi.domain.model.SwapiCharacter

sealed interface SwapiUiState {
    data object Loading : SwapiUiState
    data class Success(val character: SwapiCharacter) : SwapiUiState
    data class Error(val message: String) : SwapiUiState
}
