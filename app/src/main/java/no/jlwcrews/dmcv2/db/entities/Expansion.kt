package no.jlwcrews.dmcv2.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_expansions")
class Expansion(
    @PrimaryKey
    @ColumnInfo(name = "expansion_id")
    val expansionId: Int,

    @ColumnInfo(name = "expansion_name")
    val expansionName: String
)