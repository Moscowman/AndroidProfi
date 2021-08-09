package ru.varasoft.androidprofi.di

import dagger.Module
import dagger.Provides
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
