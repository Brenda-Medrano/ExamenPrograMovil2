package com.example.programovilexa3.swapi.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwapiPersonDto(
    val name: String,
    val height: String,
    val mass: String,
    @SerialName("hair_color") val hairColor: String,
    @SerialName("skin_color") val skinColor: String,
    @SerialName("eye_color") val eyeColor: String,
    val gender: String
)
