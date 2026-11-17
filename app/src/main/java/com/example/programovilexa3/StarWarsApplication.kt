package com.example.programovilexa3

import android.app.Application
import com.example.programovilexa3.di.dataModule
import com.example.programovilexa3.di.domainModule
import com.example.programovilexa3.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StarWarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@StarWarsApplication)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
