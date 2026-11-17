package com.example.programovilexa3.swapi.presentation

import com.example.programovilexa3.swapi.domain.model.SwapiCharacter
import com.example.programovilexa3.swapi.domain.model.SwapiPersonSummary

data class SwapiUiState(
    val characters: List<SwapiPersonSummary> = emptyList(),
    val selectedId: Int? = null,
    val selectedCharacter: SwapiCharacter? = null,
    val loadingList: Boolean = true,
    val loadingMore: Boolean = false,
    val loadingDetails: Boolean = false,
    val listError: String? = null,
    val detailError: String? = null,
    val hasNext: Boolean = true
)
