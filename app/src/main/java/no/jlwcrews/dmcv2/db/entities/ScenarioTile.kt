package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_scenario_tiles", primaryKeys = ["scenario_id", "tile_id", "status"])
data class ScenarioTile(
    @ColumnInfo(name = "scenario_id")
    val scenarioId: Int,

    @ColumnInfo(name="tile_id")
    val tileId: Int,

    @ColumnInfo(name = "status")
    val status: String
)