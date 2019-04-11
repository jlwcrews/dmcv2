package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.*
import no.jlwcrews.dmc.db.entities.Action

class ActionRepo(private val actionDao: ActionDao) {

    val allActions: LiveData<List<Action>> = actionDao.getAllActions()

    @WorkerThread
    suspend fun insert(action: Action) {
        actionDao.insert(action)
    }
}