package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_actions")
data class Action(
    @PrimaryKey
    @ColumnInfo(name="action_id")
    val actionId: Int,

    @ColumnInfo(name="action_name")
    val actionName: String
)