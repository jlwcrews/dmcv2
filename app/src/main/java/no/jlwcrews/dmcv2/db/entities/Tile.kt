package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_tiles")
data class Tile(
    @PrimaryKey
    @ColumnInfo(name = "tile_id")
    val tile: Int
)