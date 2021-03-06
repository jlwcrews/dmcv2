package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.MonsterActionDao
import no.jlwcrews.dmc.db.entities.MonsterAction

class MonsterActionRepo(private val monsterActionDao: MonsterActionDao) {

    val allMonsterActions: LiveData<List<MonsterAction>> = monsterActionDao.getAllMonsterActions()

    @WorkerThread
    suspend fun insert(monsterAction: MonsterAction) {
        monsterActionDao.insert(monsterAction)
    }

}