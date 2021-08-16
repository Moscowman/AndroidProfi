package ru.varasoft.androidprofi.model.datasource

import kotlinx.coroutines.Deferred
import ru.varasoft.androidprofi.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}
