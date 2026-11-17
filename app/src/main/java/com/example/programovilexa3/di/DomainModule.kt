package com.example.programovilexa3.di

import com.example.programovilexa3.swapi.domain.usecase.GetSwapiCharacterUseCase
import com.example.programovilexa3.swapi.domain.usecase.GetSwapiPeopleUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetSwapiCharacterUseCase(get()) }
    factory { GetSwapiPeopleUseCase(get()) }
}
