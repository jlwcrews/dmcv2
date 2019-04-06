package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_monster_types")

data class MonsterType(
    @PrimaryKey
    @ColumnInfo(name = "monster_type_id")
    val monsterTypeId: Int,

    @ColumnInfo(name = "monster_type_name")
    val monsterTypeName: String
)