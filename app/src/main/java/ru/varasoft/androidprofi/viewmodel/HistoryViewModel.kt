package ru.varasoft.androidprofi.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.utils.parseLocalSearchResults
import ru.varasoft.androidprofi.view.history.HistoryInteractor

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null) // Set View to
        // original state in
        // onStop
        super.onCleared()
    }
}
