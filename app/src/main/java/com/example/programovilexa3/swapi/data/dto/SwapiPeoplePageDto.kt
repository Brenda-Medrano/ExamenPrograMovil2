package com.example.programovilexa3.swapi.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SwapiPeoplePageDto(
    val next: String? = null,
    val results: List<SwapiPersonSummaryDto> = emptyList()
)

@Serializable
data class SwapiPersonSummaryDto(
    val name: String,
    val url: String
)
