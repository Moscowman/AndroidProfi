package ru.varasoft.androidprofi.di

import dagger.Component
import geekbrains.ru.translator.application.App
import ru.varasoft.androidprofi.view.main.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(activity: MainActivity)
}
