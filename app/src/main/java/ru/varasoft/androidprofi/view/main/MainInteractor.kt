package ru.varasoft.androidprofi.view.main

import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.model.repository.RepositoryLocal
import ru.varasoft.androidprofi.viewmodel.Interactor

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}
