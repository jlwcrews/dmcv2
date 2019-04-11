package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_scenario_tiles", primaryKeys = ["scenario_id", "tile_id", "status"])
data class ScenarioTile(
    @ColumnInfo(name = "scenario_id")
    val scenarioId: Int,

    @ColumnInfo(name="tile_id")
    val tileId: Int,

    @ColumnInfo(name = "status")
    val status: String
)