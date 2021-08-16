package ru.varasoft.androidprofi.view.main

import ru.varasoft.androidprofi.viewmodel.Interactor
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.repository.Repository
import io.reactivex.Observable

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
