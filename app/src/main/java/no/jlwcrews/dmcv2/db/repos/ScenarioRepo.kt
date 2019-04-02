package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ScenarioDao
import no.jlwcrews.dmc.db.entities.Scenario

class ScenarioRepo(private val scenarioDao: ScenarioDao) {


    val allScenarios: LiveData<List<Scenario>> = scenarioDao.getAllScenarios()

    fun selectedScenarios(expansionId: List<Int>): LiveData<List<Scenario>>{
        return scenarioDao.getScenarios(expansionId)
    }

    @WorkerThread
    suspend fun insert(scenario: Scenario) {
        scenarioDao.insert(scenario)
    }

}