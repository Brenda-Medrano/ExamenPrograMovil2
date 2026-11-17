package com.example.programovilexa3.swapi.domain.model

data class SwapiPersonSummary(val id: Int, val name: String)

data class SwapiPeoplePage(
    val characters: List<SwapiPersonSummary>,
    val hasNext: Boolean
)
