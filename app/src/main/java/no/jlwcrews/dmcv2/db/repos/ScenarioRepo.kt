package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ScenarioDao
import no.jlwcrews.dmc.db.entities.Scenario

class ScenarioRepo(private val scenarioDao: ScenarioDao) {


    val allScenarios: LiveData<List<Scenario>> = scenarioDao.getAllScenarios()

    fun selectedScenario(scenarioId: Int): LiveData<Scenario>{
        return scenarioDao.getScenarioById(scenarioId)
    }

    fun selectedScenarios(expansionId: List<Int>): LiveData<List<Scenario>>{
        return scenarioDao.getScenariosByExpansion(expansionId)
    }

    @WorkerThread
    suspend fun insert(scenario: Scenario) {
        scenarioDao.insert(scenario)
    }

}