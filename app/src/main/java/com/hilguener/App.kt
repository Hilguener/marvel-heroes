package com.hilguener

import android.app.Application
import com.hilguener.marvelsuperheroes.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules = androidModule)
        }
    }
}