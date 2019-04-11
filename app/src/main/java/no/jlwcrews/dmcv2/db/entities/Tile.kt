package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_tiles")
data class Tile(
    @PrimaryKey
    @ColumnInfo(name = "tile_id")
    val tile: Int
)