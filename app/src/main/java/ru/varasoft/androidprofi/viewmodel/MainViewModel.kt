package ru.varasoft.androidprofi.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.utils.parseSearchResults
import ru.varasoft.androidprofi.view.main.MainInteractor

class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) = withContext(Dispatchers.IO) {
        liveDataForViewToObserve.postValue(parseSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

}
