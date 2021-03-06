package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.MonsterTypeDao
import no.jlwcrews.dmc.db.entities.MonsterType

class MonsterTypeRepo(private val monsterTypeDao: MonsterTypeDao) {

    val allMonsterTypes: LiveData<List<MonsterType>> = monsterTypeDao.getMonsterTypes()

    @WorkerThread
    suspend fun insert(monsterType: MonsterType) {
        monsterTypeDao.insert(monsterType)
    }
}