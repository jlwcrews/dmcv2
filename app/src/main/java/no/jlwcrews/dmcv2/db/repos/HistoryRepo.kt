package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.HistoryDao
import no.jlwcrews.dmc.db.entities.History

class HistoryRepo(private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()

    @WorkerThread
    suspend fun insert(history: History) {
        historyDao.insert(history)
    }
}