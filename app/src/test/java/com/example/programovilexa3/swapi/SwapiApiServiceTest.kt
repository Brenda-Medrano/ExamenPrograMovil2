package com.example.programovilexa3.swapi

import com.example.programovilexa3.swapi.data.service.SwapiApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SwapiApiServiceTest {
    @Test fun `consulta ruta correcta y deserializa JSON de SWAPI`() = runBlocking {
        val client = HttpClient(MockEngine { request ->
            assertEquals("/api/people/1/", request.url.encodedPath)
            respond(
                content = """{
                    "name":"Luke Skywalker", "height":"172", "mass":"77",
                    "hair_color":"blond", "skin_color":"fair", "eye_color":"blue",
                    "gender":"male", "films":[]
                }""".trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        try {
            val luke = SwapiApiService(client).getCharacter(1)
            assertEquals("Luke Skywalker", luke.name)
            assertEquals("blond", luke.hairColor)
        } finally {
            client.close()
        }
    }
}
