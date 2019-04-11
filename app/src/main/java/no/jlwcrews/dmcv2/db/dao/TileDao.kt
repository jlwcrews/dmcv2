package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.Tile

@Dao
interface TileDao{
    @Query("select * from dmc_tiles")
    fun getAllTiles(): LiveData<List<Tile>>

    @Query("delete from dmc_tiles")
    fun deleteAll()

    @Insert
    fun insert(vararg tile: Tile)

    @Delete
    fun delete(vararg tile: Tile)

    @Update
    fun update(vararg tile: Tile)
}