package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_monster_actions", primaryKeys = ["monster_id", "monster_action_id", "monster_action_sequence"])
data class MonsterAction(

    @ColumnInfo(name = "monster_id")
    val monsterId: Int,

    @ColumnInfo(name = "monster_action_id")
    val monsterActionId: Int,

    @ColumnInfo(name = "monster_action_sequence")
    val monsterActionSequence: Int
)
