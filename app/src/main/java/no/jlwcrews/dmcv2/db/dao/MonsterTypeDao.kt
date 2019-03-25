package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.MonsterType

@Dao
interface MonsterTypeDao{

    @Query("select * from dmc_monster_types")
    fun getMonsterTypes(): LiveData<List<MonsterType>>

    @Query("select * from dmc_monster_types where monster_type_id = :monsterTypeId")
    fun getMonsterType(monsterTypeId: Int): MonsterType

    @Insert
    fun insert(vararg monsterType: MonsterType)

    @Delete
    fun delete(vararg monsterType: MonsterType)

    @Update
    fun update(vararg monsterType: MonsterType)
}
