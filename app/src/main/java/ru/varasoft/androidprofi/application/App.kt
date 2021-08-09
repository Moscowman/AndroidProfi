package geekbrains.ru.translator.application

import android.app.Application
import ru.varasoft.androidprofi.di.AppComponent
import ru.varasoft.androidprofi.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .build()
    }
}
