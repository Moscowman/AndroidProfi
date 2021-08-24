package ru.varasoft.androidprofi.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.varasoft.androidprofi.koin.application
import ru.varasoft.androidprofi.koin.historyScreen
import ru.varasoft.androidprofi.koin.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
