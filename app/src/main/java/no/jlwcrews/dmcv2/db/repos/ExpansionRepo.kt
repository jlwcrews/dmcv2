package no.jlwcrews.dmcv2.db.repos

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmcv2.db.dao.ExpansionDao
import no.jlwcrews.dmcv2.db.entities.Expansion

class ExpansionRepo(private val expansionDao: ExpansionDao) {

    val allExpansions: LiveData<List<Expansion>> = expansionDao.getAllExpansions()

    fun getExpansionsByApiId(expansionId: List<Int>): LiveData<List<Expansion>>{
        return expansionDao.getExpansionsFromApi(expansionId)
    }


    @WorkerThread
    suspend fun insert(exansion: Expansion) {
        expansionDao.insert(exansion)
    }
}