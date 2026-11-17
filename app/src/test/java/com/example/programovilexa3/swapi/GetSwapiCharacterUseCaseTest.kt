package com.example.programovilexa3.swapi

import com.example.programovilexa3.swapi.domain.model.SwapiCharacter
import com.example.programovilexa3.swapi.domain.repository.SwapiRepository
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiCharacterUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSwapiCharacterUseCaseTest {
    @Test fun `el caso de uso solicita el personaje numero uno`() = runBlocking {
        var requestedId = 0
        val fakeRepository = object : SwapiRepository {
            override suspend fun getCharacter(id: Int): Result<SwapiCharacter> {
                requestedId = id
                return Result.success(SwapiCharacter("Luke Skywalker", "172", "77", "blond", "fair", "blue", "male"))
            }
        }
        val result = GetSwapiCharacterUseCase(fakeRepository)()
        assertEquals(1, requestedId)
        assertEquals("Luke Skywalker", result.getOrThrow().name)
    }
}
