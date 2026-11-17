package com.example.programovilexa3.swapi.data.datasource

import com.example.programovilexa3.swapi.data.dto.SwapiPersonDto
import com.example.programovilexa3.swapi.data.dto.SwapiPeoplePageDto

interface SwapiRemoteDataSource {
    suspend fun getCharacter(id: Int): SwapiPersonDto
    suspend fun getPeoplePage(page: Int): SwapiPeoplePageDto
}
