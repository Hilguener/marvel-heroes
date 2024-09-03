package com.hilguener

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.hilguener.marvelsuperheroes.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules = androidModule)
        }
    }
}
