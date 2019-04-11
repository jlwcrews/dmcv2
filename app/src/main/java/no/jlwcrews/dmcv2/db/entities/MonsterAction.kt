package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_monster_actions", primaryKeys = ["monster_id", "monster_action_id", "monster_action_sequence"])
data class MonsterAction(

    @ColumnInfo(name = "monster_id")
    val monsterId: Int,

    @ColumnInfo(name = "monster_action_id")
    val monsterActionId: Int,

    @ColumnInfo(name = "monster_action_sequence")
    val monsterActionSequence: Int
)
