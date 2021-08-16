package ru.varasoft.androidprofi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.varasoft.androidprofi.model.data.AppState

abstract class BaseViewModel<T : AppState>(
    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData()

) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun handleError(error: Throwable)
}
