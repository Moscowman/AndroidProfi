package ru.varasoft.androidprofi.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
