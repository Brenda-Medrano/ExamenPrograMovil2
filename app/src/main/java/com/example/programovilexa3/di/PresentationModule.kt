package com.example.programovilexa3.di

import com.example.programovilexa3.swapi.presentation.SwapiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SwapiViewModel(get(), get()) }
}
