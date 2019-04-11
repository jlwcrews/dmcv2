package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.TileDao
import no.jlwcrews.dmc.db.entities.Tile

class TileRepo(private val tileDao: TileDao) {

    val allTiles: LiveData<List<Tile>> = tileDao.getAllTiles()

    @WorkerThread
    suspend fun insert(tile: Tile) {
        tileDao.insert(tile)
    }
}