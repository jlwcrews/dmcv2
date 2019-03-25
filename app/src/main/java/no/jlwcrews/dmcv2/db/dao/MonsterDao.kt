package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.Monster

@Dao
interface MonsterDao{
    @Query("select * from dmc_monsters order by monster_id asc")
    fun getAllMonsters(): LiveData<List<Monster>>

    @Query("delete from dmc_monsters")
    fun deleteAll()

    @Query("select * from dmc_monsters where monster_id = :monsterId")
    fun getMonster(monsterId: Int): Monster

    @Insert
    fun insert(vararg monster: Monster)

    @Delete
    fun delete(vararg monster: Monster)

    @Update
    fun update(vararg monster: Monster)
}
