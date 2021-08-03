package ru.varasoft.androidprofi.model.repository

import ru.varasoft.androidprofi.model.data.DataModel
import ru.varasoft.androidprofi.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
