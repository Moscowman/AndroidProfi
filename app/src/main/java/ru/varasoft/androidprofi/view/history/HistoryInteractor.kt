package ru.varasoft.androidprofi.view.history

import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.repository.Repository
import ru.varasoft.androidprofi.model.repository.RepositoryLocal
import ru.varasoft.androidprofi.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}