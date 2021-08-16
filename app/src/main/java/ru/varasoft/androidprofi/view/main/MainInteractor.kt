package ru.varasoft.androidprofi.view.main

import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.viewmodel.Interactor

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}
