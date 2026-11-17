package com.example.programovilexa3.swapi.data.mapper

import com.example.programovilexa3.swapi.data.dto.SwapiPeoplePageDto
import com.example.programovilexa3.swapi.domain.model.SwapiPeoplePage
import com.example.programovilexa3.swapi.domain.model.SwapiPersonSummary

fun SwapiPeoplePageDto.toDomain(): SwapiPeoplePage = SwapiPeoplePage(
    characters = results.map { item ->
        val id = item.url.trimEnd('/').substringAfterLast('/').toIntOrNull()
            ?: error("La API devolvió una URL de personaje inválida: ${item.url}")
        SwapiPersonSummary(id = id, name = item.name)
    },
    hasNext = !next.isNullOrBlank()
)
