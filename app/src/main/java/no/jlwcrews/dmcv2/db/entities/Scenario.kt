package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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