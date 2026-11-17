package com.example.programovilexa3.swapi.data.mapper

import com.example.programovilexa3.swapi.data.dto.SwapiPersonDto
import com.example.programovilexa3.swapi.domain.model.SwapiCharacter

fun SwapiPersonDto.toDomain() = SwapiCharacter(
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    gender = gender
)
