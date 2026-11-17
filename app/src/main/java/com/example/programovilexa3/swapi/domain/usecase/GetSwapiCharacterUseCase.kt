package com.example.programovilexa3.swapi.domain.usecase

import com.example.programovilexa3.swapi.domain.model.SwapiCharacter
import com.example.programovilexa3.swapi.domain.repository.SwapiRepository

class GetSwapiCharacterUseCase(private val repository: SwapiRepository) {
    suspend operator fun invoke(id: Int = 1): Result<SwapiCharacter> {
        require(id > 0) { "El ID debe ser positivo" }
        return repository.getCharacter(id)
    }
}
