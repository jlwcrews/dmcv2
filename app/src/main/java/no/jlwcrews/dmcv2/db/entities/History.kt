package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_history")
data class History(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id")
    val historyId: Long,

    @ColumnInfo(name="history_date")
    var historyDate: String,

    @ColumnInfo(name="history_location")
    val historyLocation: String,

    @ColumnInfo(name="history_scenario_id")
    val historyScenarioId: Int
)
