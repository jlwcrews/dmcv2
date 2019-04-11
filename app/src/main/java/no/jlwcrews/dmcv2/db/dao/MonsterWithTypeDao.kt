package no.jlwcrews.dmcv2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import no.jlwcrews.dmcv2.db.models.MonsterWithType

@Dao
interface MonsterWithTypeDao{

    @Query("select * from dmc_monsters as m, dmc_monster_types as mt where m.monster_id = mt.monster_type_id")
    fun getMonstersWithMonsterTypes(): LiveData<List<MonsterWithType>>

    @Query("select * from dmc_monsters as m, dmc_monster_types as mt where m.monster_expansion_id IN (:expansionId) and  m.monster_type = mt.monster_type_id")
    fun getMonstersWithTypesByExpansion(expansionId: List<Int>): LiveData<List<MonsterWithType>>

    @Query("select * from dmc_monsters as m, dmc_monster_types as mt where m.monster_id IN (:monsterId) and  m.monster_type = mt.monster_type_id")
    fun getMonstersWithTypesById(monsterId: List<Int>): LiveData<List<MonsterWithType>>
}