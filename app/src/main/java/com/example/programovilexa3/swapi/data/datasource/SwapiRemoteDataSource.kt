package com.example.programovilexa3.swapi.data.datasource

import com.example.programovilexa3.swapi.data.dto.SwapiPersonDto

interface SwapiRemoteDataSource {
    suspend fun getCharacter(id: Int): SwapiPersonDto
}
