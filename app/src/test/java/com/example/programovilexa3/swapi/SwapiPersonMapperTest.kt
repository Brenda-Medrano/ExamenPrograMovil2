package com.example.programovilexa3.swapi

import com.example.programovilexa3.swapi.data.dto.SwapiPersonDto
import com.example.programovilexa3.swapi.data.mapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class SwapiPersonMapperTest {
    @Test fun `mapea los siete campos del ejercicio`() {
        val dto = SwapiPersonDto("Luke Skywalker", "172", "77", "blond", "fair", "blue", "male")
        val character = dto.toDomain()
        assertEquals("Luke Skywalker", character.name)
        assertEquals("172", character.height)
        assertEquals("77", character.mass)
        assertEquals("blond", character.hairColor)
        assertEquals("fair", character.skinColor)
        assertEquals("blue", character.eyeColor)
        assertEquals("male", character.gender)
    }
}
