package ru.varasoft.androidprofi.view.base

import ru.varasoft.androidprofi.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}
