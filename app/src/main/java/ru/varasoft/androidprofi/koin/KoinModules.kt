package ru.varasoft.androidprofi.koin

import androidx.room.Room
import org.koin.dsl.module
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.datasource.RetrofitImplementation
import ru.varasoft.androidprofi.model.datasource.RoomDataBaseImplementation
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.model.repository.RepositoryImplementation
import ru.varasoft.androidprofi.model.repository.RepositoryImplementationLocal
import ru.varasoft.androidprofi.model.repository.RepositoryLocal
import ru.varasoft.androidprofi.room.HistoryDataBase
import ru.varasoft.androidprofi.view.history.HistoryInteractor
import ru.varasoft.androidprofi.view.main.MainInteractor
import ru.varasoft.androidprofi.viewmodel.HistoryViewModel
import ru.varasoft.androidprofi.viewmodel.MainViewModel

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }

    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> { RepositoryImplementation(
        RetrofitImplementation()
    ) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
        RoomDataBaseImplementation(get())
    ) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}