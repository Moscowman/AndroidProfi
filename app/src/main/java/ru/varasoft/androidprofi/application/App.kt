package geekbrains.ru.translator.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.varasoft.androidprofi.koin.application
import ru.varasoft.androidprofi.koin.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
