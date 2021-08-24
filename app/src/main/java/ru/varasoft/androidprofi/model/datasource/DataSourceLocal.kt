package ru.varasoft.androidprofi.model.datasource

import ru.varasoft.androidprofi.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}