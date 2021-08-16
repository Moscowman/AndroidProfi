package ru.varasoft.androidprofi.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.varasoft.androidprofi.di.NAME_LOCAL
import ru.varasoft.androidprofi.di.NAME_REMOTE
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.datasource.RetrofitImplementation
import ru.varasoft.androidprofi.model.datasource.RoomDataBaseImplementation
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.model.repository.RepositoryImplementation
import ru.varasoft.androidprofi.view.main.MainInteractor
import ru.varasoft.androidprofi.viewmodel.MainViewModel

val application = module {

    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
        RoomDataBaseImplementation()
    ) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}