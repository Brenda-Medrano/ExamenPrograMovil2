package com.example.programovilexa3.swapi.domain.repository

import com.example.programovilexa3.swapi.domain.model.SwapiCharacter

interface SwapiRepository {
    suspend fun getCharacter(id: Int): Result<SwapiCharacter>
}
