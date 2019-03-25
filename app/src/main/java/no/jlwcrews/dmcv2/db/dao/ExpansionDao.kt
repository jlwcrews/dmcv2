package no.jlwcrews.dmcv2.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
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