package no.jlwcrews.dmcv2.db.repos

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmcv2.db.dao.ExpansionDao
import no.jlwcrews.dmcv2.db.entities.Expansion

class ExpansionRepo(private val expansionDao: ExpansionDao) {

    val allExpansions: LiveData<List<Expansion>> = expansionDao.getAllExpansions()

    @WorkerThread
    suspend fun insert(exansion: Expansion) {
        expansionDao.insert(exansion)
    }
}