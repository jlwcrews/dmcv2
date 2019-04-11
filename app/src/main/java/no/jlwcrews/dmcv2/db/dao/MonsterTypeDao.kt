package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.MonsterType

@Dao
interface MonsterTypeDao{

    @Query("select * from dmc_monster_types")
    fun getMonsterTypes(): LiveData<List<MonsterType>>

    @Query("select * from dmc_monster_types where monster_type_id = :monsterTypeId")
    fun getMonsterType(monsterTypeId: Int): MonsterType

    @Query("delete from dmc_monster_types")
    fun deleteAll()

    @Insert
    fun insert(vararg monsterType: MonsterType)

    @Delete
    fun delete(vararg monsterType: MonsterType)

    @Update
    fun update(vararg monsterType: MonsterType)
}
