package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.Monster

@Dao
interface MonsterDao{
    @Query("select * from dmc_monsters order by monster_id asc")
    fun getAllMonsters(): LiveData<List<Monster>>

    @Query("delete from dmc_monsters")
    fun deleteAll()

    @Query("select * from dmc_monsters where monster_expansion_id IN (:expansionId)")
    fun getMonstersByExpansion(expansionId: List<Int>): LiveData<List<Monster>>

    @Query("select * from dmc_monsters where monster_id IN (:monsterId)")
    fun getMonstersById(monsterId: List<Int>): LiveData<List<Monster>>

    @Query("select * from dmc_monsters where monster_id = :monsterId")
    fun getMonster(monsterId: Int): Monster

    @Insert
    fun insert(vararg monster: Monster)

    @Delete
    fun delete(vararg monster: Monster)

    @Update
    fun update(vararg monster: Monster)
}
