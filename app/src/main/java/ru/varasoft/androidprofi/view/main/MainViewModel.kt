package ru.varasoft.androidprofi.view.main

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.model.datasource.DataSourceLocal
import ru.varasoft.androidprofi.model.datasource.DataSourceRemote
import ru.varasoft.androidprofi.model.repository.RepositoryImplementation
import ru.varasoft.androidprofi.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                liveDataForViewToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}
