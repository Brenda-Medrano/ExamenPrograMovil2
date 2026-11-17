package com.example.programovilexa3.di

import com.example.programovilexa3.swapi.data.datasource.SwapiRemoteDataSource
import com.example.programovilexa3.swapi.data.repository.SwapiRepositoryImpl
import com.example.programovilexa3.swapi.data.service.SwapiApiService
import com.example.programovilexa3.swapi.domain.repository.SwapiRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient(OkHttp) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
                socketTimeoutMillis = 15_000
            }
        }
    }
    single<SwapiRemoteDataSource> { SwapiApiService(get()) }
    single<SwapiRepository> { SwapiRepositoryImpl(get()) }
}
