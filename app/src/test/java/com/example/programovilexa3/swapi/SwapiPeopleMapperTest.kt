package com.example.programovilexa3.swapi

import com.example.programovilexa3.swapi.data.dto.SwapiPeoplePageDto
import com.example.programovilexa3.swapi.data.dto.SwapiPersonSummaryDto
import com.example.programovilexa3.swapi.data.mapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SwapiPeopleMapperTest {
    @Test fun `mapea ids reales de URL y conserva orden`() {
        val dto = SwapiPeoplePageDto(
            next = "https://swapi.dev/api/people/?page=2",
            results = listOf(
                SwapiPersonSummaryDto("Luke", "https://swapi.dev/api/people/1/"),
                SwapiPersonSummaryDto("R2-D2", "https://swapi.dev/api/people/3/")
            )
        )
        val page = dto.toDomain()
        assertEquals(listOf(1, 3), page.characters.map { it.id })
        assertEquals(listOf("Luke", "R2-D2"), page.characters.map { it.name })
        assertTrue(page.hasNext)
        assertFalse(dto.copy(next = null).toDomain().hasNext)
    }
}
