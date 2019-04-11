package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_actions")
data class Action(
    @PrimaryKey
    @ColumnInfo(name="action_id")
    val actionId: Int,

    @ColumnInfo(name="action_name")
    val actionName: String
)