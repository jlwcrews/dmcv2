package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_scenarios")
data class Scenario(
    @PrimaryKey
    @ColumnInfo(name = "scenario_id")
    val scenarioId: Int,

    @ColumnInfo(name = "scenario_name")
    val scenarioName: String,

    @ColumnInfo(name = "scenario_objective")
    val scenarioObjective: String,

    @ColumnInfo(name = "scenario_expansion_id")
    val scenarioExpansionId: Int
)