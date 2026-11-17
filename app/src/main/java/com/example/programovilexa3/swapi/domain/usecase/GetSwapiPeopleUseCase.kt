package com.example.programovilexa3.swapi.domain.usecase

import com.example.programovilexa3.swapi.domain.model.SwapiPeoplePage
import com.example.programovilexa3.swapi.domain.repository.SwapiPeopleRepository

class GetSwapiPeopleUseCase(private val repository: SwapiPeopleRepository) {
    suspend operator fun invoke(page: Int): Result<SwapiPeoplePage> {
        require(page > 0) { "La página debe ser mayor a cero" }
        return repository.getPeoplePage(page)
    }
}
