package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ActionDao
import no.jlwcrews.dmc.db.dao.MonsterActionDao
import no.jlwcrews.dmc.db.dao.MonsterDao
import no.jlwcrews.dmc.db.dao.MonsterTypeDao
import no.jlwcrews.dmc.db.entities.Monster


class MonsterRepo(private val monsterDao: MonsterDao) {

    val allMonsters: LiveData<List<Monster>> = monsterDao.getAllMonsters()


    fun selectedMonsters(expansionId: List<Int>): LiveData<List<Monster>>{
        return monsterDao.getMonstersByExpansion(expansionId)
    }
    @WorkerThread
    suspend fun insert(monster: Monster) {
        monsterDao.insert(monster)
    }

}