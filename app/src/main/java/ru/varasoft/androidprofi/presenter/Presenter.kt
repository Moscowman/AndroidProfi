package ru.varasoft.androidprofi.presenter

import ru.varasoft.androidprofi.model.data.AppState
import ru.varasoft.androidprofi.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
