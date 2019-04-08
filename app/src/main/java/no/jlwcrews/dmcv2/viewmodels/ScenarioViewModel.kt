package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmc.db.repo.ScenarioRepo
import no.jlwcrews.dmcv2.db.DmcDatabase

import kotlin.coroutines.CoroutineContext

class ScenarioViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ScenarioRepo
    lateinit var scenarios: LiveData<List<Scenario>>

    init {
        val scenarioDao = DmcDatabase.getDatabase(application, scope).scenarioDao()
        repository = ScenarioRepo(scenarioDao)
    }

    fun initScenarios(expansions: List<Int>){
        scenarios = repository.selectedScenarios(expansions)
    }

    fun insert(scenario: Scenario) = scope.launch(Dispatchers.IO) {
        repository.insert(scenario)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}