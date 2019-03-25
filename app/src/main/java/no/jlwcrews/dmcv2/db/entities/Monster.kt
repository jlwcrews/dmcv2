package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_monsters")
data class Monster(
    @PrimaryKey
    @ColumnInfo(name = "monster_id")
    val monsterId: Int,

    @ColumnInfo(name = "monster_name")
    val monsterName: String,

    @ColumnInfo(name = "monster_horror")
    val monsterHorror: Int,

    @ColumnInfo(name = "monster_trap")
    val monsterTrap: Int,

    @ColumnInfo(name = "monster_damage")
    val monsterDamage: Int,

    @ColumnInfo(name = "monster_range")
    val monsterRange: Int,

    @ColumnInfo(name = "monster_hp")
    val monsterHp: Int,

    @ColumnInfo(name = "monster_resistance")
    val monsterResistance: Int,

    @ColumnInfo(name = "monster_special")
    val monsterSpecial: String,

    @ColumnInfo(name = "monster_type")
    val monsterType: Int,

    @ColumnInfo(name = "monster_expansion_id")
    val monsterExpansionId: Int
)
