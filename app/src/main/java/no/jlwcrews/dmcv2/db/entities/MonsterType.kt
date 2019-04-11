package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_monster_types")

data class MonsterType(
    @PrimaryKey
    @ColumnInfo(name = "monster_type_id")
    val monsterTypeId: Int,

    @ColumnInfo(name = "monster_type_name")
    val monsterTypeName: String
)