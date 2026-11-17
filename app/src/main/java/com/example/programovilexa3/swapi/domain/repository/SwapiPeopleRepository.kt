package com.example.programovilexa3.swapi.domain.repository

import com.example.programovilexa3.swapi.domain.model.SwapiPeoplePage

interface SwapiPeopleRepository {
    suspend fun getPeoplePage(page: Int): Result<SwapiPeoplePage>
}
