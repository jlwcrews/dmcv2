package no.jlwcrews.dmc.db.repo

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.ScenarioCardDao
import no.jlwcrews.dmc.db.entities.ScenarioCard

class ScenarioCardRepo(private val scenarioCardDao: ScenarioCardDao) {

    val allScenarioCards: LiveData<List<ScenarioCard>> = scenarioCardDao.getAllScenarioCards()

    @WorkerThread
    suspend fun insert(scenarioCard: ScenarioCard) {
        scenarioCardDao.insert(scenarioCard)
    }
}