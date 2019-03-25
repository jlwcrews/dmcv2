package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_history")
data class History(

    @PrimaryKey
    @ColumnInfo(name = "history_id")
    val historyId: Int,

    @ColumnInfo(name="history_date")
    val historyDate: String,

    @ColumnInfo(name="history_location")
    val historyLocation: String,

    @ColumnInfo(name="history_scenario_id")
    val historyScenarioId: Int,

    @ColumnInfo(name="history_result")
    val historyResult: String

)
