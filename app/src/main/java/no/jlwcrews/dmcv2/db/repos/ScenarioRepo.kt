package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ScenarioCardDao
import no.jlwcrews.dmc.db.dao.ScenarioDao
import no.jlwcrews.dmc.db.dao.ScenarioTileDao
import no.jlwcrews.dmc.db.dao.TileDao
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmc.db.entities.ScenarioTile

class ScenarioRepo(private val scenarioDao: ScenarioDao,
                   private val tileDao: TileDao,
                   private val scenarioTileDao: ScenarioTileDao,
                   private val scenarioCardDao: ScenarioCardDao) {


    val allScenarios: LiveData<List<Scenario>> = scenarioDao.getAllScenarios()

    @WorkerThread
    suspend fun insert(scenario: Scenario) {
        scenarioDao.insert(scenario)
    }

}

/*fun getScenarioTiles(scenarioId: Int): List<Pair<Int, String>>{
    val scenarioTileEntityList: List<ScenarioTile> = scenarioTileDao.getScenarioTiles(scenarioId)
    val scenarioTileList: MutableList<Pair<Int, String>> = mutableListOf()
    for (scenarioTileEntity in scenarioTileEntityList){
        val scenarioTile: Pair<Int, String>
        val scenarioTileId = scenarioTileEntity.tileId
        val scenarioTileStatus = scenarioTileEntity.status
        scenarioTile = Pair(scenarioTileId, scenarioTileStatus)
        scenarioTileList.add(scenarioTile)
    }
    return scenarioTileList
}*/