package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.MonsterAction

@Dao
interface MonsterActionDao{

    @Query("select * from dmc_monster_actions")
    fun getAllMonsterActions(): LiveData<List<MonsterAction>>

    @Query("select * from dmc_monster_actions where monster_id = :monsterId")
    fun getActionsForMonster(monsterId: Int): LiveData<List<MonsterAction>>

    @Query("delete from dmc_monster_actions")
    fun deleteAll()

    @Insert
    fun insert(vararg monsterAction: MonsterAction)

    @Delete
    fun delete(vararg monsterAction: MonsterAction)

    @Update
    fun update(vararg monsterAction: MonsterAction)
}