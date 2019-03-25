package no.jlwcrews.dmcv2.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_expansions")
class Expansion(
    @PrimaryKey
    @ColumnInfo(name = "expansion_id")
    val expansionId: Int,

    @ColumnInfo(name = "expansion_name")
    val expansionName: String
)