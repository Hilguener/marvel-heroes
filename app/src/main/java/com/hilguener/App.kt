package com.hilguener

import android.app.Application
import com.google.firebase.FirebaseApp
import com.hilguener.marvelsuperheroes.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules = androidModule)
        }
    }
}
