package no.jlwcrews.dmcv2.db.repos

import androidx.lifecycle.LiveData
import no.jlwcrews.dmcv2.db.dao.MonsterActionWithActionDao
import no.jlwcrews.dmcv2.db.models.MonsterActionWithAction

class MonsterActionsWithActionRepo(private val monsterActionWithActionDao: MonsterActionWithActionDao) {

    val allMonsterActionsWithAction: LiveData<List<MonsterActionWithAction>> = monsterActionWithActionDao.getMonsterActionsWithActions()

    fun getMonsterActionsWithActionByMonsterId(monsterId: List<Int>): LiveData<List<MonsterActionWithAction>> {
        return monsterActionWithActionDao.getMonsterActionsWithActionsByMonsterId(monsterId)
    }

}
