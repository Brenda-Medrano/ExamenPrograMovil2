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

class SwapiPeopleApiServiceTest {
    @Test fun `consulta la pagina dos y obtiene personajes`() = runBlocking {
        val client = HttpClient(MockEngine { request ->
            assertEquals("/api/people/", request.url.encodedPath)
            assertEquals("2", request.url.parameters["page"])
            respond(
                content = """{
                    "count": 82, "next": null,
                    "results": [
                        {"name": "Han Solo", "url": "https://swapi.dev/api/people/14/"}
                    ]
                }""".trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        try {
            val page = SwapiApiService(client).getPeoplePage(2)
            assertEquals("Han Solo", page.results.single().name)
            assertEquals(null, page.next)
        } finally {
            client.close()
        }
    }
}
