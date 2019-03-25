package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.Tile

@Dao
interface TileDao{
    @Query("select * from dmc_tiles")
    fun getAllTiles(): LiveData<List<Tile>>

    @Insert
    fun insert(vararg tile: Tile)

    @Delete
    fun delete(vararg tile: Tile)

    @Update
    fun update(vararg tile: Tile)
}