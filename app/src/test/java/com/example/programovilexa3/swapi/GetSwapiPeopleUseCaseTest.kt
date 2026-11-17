package com.example.programovilexa3.swapi

import com.example.programovilexa3.swapi.domain.model.SwapiPeoplePage
import com.example.programovilexa3.swapi.domain.repository.SwapiPeopleRepository
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiPeopleUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetSwapiPeopleUseCaseTest {
    @Test fun `envia el numero de pagina al repositorio`() = runBlocking {
        var requestedPage = 0
        val fake = object : SwapiPeopleRepository {
            override suspend fun getPeoplePage(page: Int): Result<SwapiPeoplePage> {
                requestedPage = page
                return Result.success(SwapiPeoplePage(emptyList(), false))
            }
        }
        val result = GetSwapiPeopleUseCase(fake)(2)
        assertTrue(result.isSuccess)
        assertEquals(2, requestedPage)
    }
}
