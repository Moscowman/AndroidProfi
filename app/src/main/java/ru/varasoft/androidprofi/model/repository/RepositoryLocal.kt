package ru.varasoft.androidprofi.model.repository

import ru.varasoft.androidprofi.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}