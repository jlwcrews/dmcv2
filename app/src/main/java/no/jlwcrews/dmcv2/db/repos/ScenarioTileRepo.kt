package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ScenarioTileDao
import no.jlwcrews.dmc.db.entities.ScenarioTile

class ScenarioTileRepo(private val scenarioTileDao: ScenarioTileDao) {

    val allScenarioTiles: LiveData<List<ScenarioTile>> = scenarioTileDao.getAllScenarioTiles()

    @WorkerThread
    suspend fun insert(scenarioTile: ScenarioTile) {
        scenarioTileDao.insert(scenarioTile)
    }
}