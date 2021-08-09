package ru.varasoft.androidprofi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.varasoft.androidprofi.view.main.MainViewModel

@Module(includes = [InteractorModule::class])
internal class ViewModelModule {

    @Provides
    internal fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal fun mainViewModel(mainViewModel: MainViewModel): ViewModel = mainViewModel
}
