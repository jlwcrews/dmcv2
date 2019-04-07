package no.jlwcrews.dmcv2.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import no.jlwcrews.dmcv2.db.models.MonsterActionWithAction

@Dao
interface MonsterActionWithActionDao{

    @Query("select * from dmc_monster_actions as m, dmc_actions as a where m.monster_action_id = a.action_id")
    fun getMonsterActionsWithActions(): LiveData<List<MonsterActionWithAction>>

    @Query("select * from dmc_monster_actions as m, dmc_actions as a where m.monster_id IN (:monsterId) and  m.monster_action_id = a.action_id")
    fun getMonsterActionsWithActionsByMonsterId(monsterId: List<Int>): LiveData<List<MonsterActionWithAction>>
}