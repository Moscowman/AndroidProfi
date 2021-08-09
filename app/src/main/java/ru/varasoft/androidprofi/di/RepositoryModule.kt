package ru.varasoft.androidprofi.di

import dagger.Module
import dagger.Provides
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.datasource.DataSource
import ru.varasoft.androidprofi.model.datasource.RetrofitImplementation
import ru.varasoft.androidprofi.model.datasource.RoomDataBaseImplementation
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}
