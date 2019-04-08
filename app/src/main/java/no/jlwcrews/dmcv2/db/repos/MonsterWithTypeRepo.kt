package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import no.jlwcrews.dmcv2.db.dao.MonsterWithTypeDao
import no.jlwcrews.dmcv2.db.models.MonsterWithType


class MonsterWithTypeRepo(private val monsterWithTypeDao: MonsterWithTypeDao) {

    val allMonstersWithType: LiveData<List<MonsterWithType>> = monsterWithTypeDao.getMonstersWithMonsterTypes()

    fun selectedMonstersWithType(expansionId: List<Int>): LiveData<List<MonsterWithType>>{
        return monsterWithTypeDao.getMonstersWithTypesByExpansion(expansionId)
    }

    fun selectedMonstersWithTypeById(monsterId: List<Int>): LiveData<List<MonsterWithType>>{
        return monsterWithTypeDao.getMonstersWithTypesById(monsterId)
    }

}