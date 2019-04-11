package no.jlwcrews.dmcv2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.jlwcrews.dmcv2.db.entities.Expansion

@Dao
interface ExpansionDao {

    @Query("SELECT * from dmc_expansions")
    fun getAllExpansions(): LiveData<List<Expansion>>

    @Insert
    fun insert(expansion: Expansion)

    @Query("DELETE FROM dmc_expansions")
    fun deleteAll()
}