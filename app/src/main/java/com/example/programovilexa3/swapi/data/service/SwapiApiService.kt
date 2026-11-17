package com.example.programovilexa3.swapi.data.service

import com.example.programovilexa3.swapi.data.datasource.SwapiRemoteDataSource
import com.example.programovilexa3.swapi.data.dto.SwapiPersonDto
import com.example.programovilexa3.swapi.data.dto.SwapiPeoplePageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SwapiApiService(private val client: HttpClient) : SwapiRemoteDataSource {
    override suspend fun getCharacter(id: Int): SwapiPersonDto =
        client.get("https://swapi.dev/api/people/$id/").body()

    override suspend fun getPeoplePage(page: Int): SwapiPeoplePageDto =
        client.get("https://swapi.dev/api/people/") {
            parameter("page", page)
        }.body()
}
