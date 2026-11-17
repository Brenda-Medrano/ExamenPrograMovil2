package com.example.programovilexa3.di

import com.example.programovilexa3.swapi.domain.usecase.GetSwapiCharacterUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetSwapiCharacterUseCase(get()) }
}
