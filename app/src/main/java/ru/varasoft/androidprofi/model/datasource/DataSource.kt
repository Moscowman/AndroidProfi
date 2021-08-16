package ru.varasoft.androidprofi.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}
