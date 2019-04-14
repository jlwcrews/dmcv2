package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmc.db.repo.CharacterRepo
import no.jlwcrews.dmcv2.db.repos.HistoryRepo
import no.jlwcrews.dmc.db.repo.MonsterWithTypeRepo
import no.jlwcrews.dmc.db.repo.ScenarioRepo
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.models.MonsterWithType
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import kotlin.coroutines.CoroutineContext

class SummaryViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repositoryCharacter: CharacterRepo
    private val repositoryMonsters: MonsterWithTypeRepo
    private val repositoryScenario: ScenarioRepo
    private val repositoryHistory: HistoryRepo

    lateinit var characters: LiveData<List<PlayerCharacter>>
    lateinit var monsters: LiveData<List<MonsterWithType>>
    lateinit var scenario: LiveData<Scenario>

    init {
        val characterDao = DmcDatabase.getDatabase(application, scope).characterDao()
        val monsterDao = DmcDatabase.getDatabase(application, scope).monsterWithTypeDao()
        val scenarioDao = DmcDatabase.getDatabase(application, scope).scenarioDao()
        val historyDao = DmcDatabase.getDatabase(application, scope).historyDao()

        repositoryCharacter = CharacterRepo(characterDao)
        repositoryMonsters = MonsterWithTypeRepo(monsterDao)
        repositoryScenario = ScenarioRepo(scenarioDao)
        repositoryHistory = HistoryRepo(historyDao)
    }

    fun initViewModel(newGameContainer: NewGameContainer){
        characters = repositoryCharacter.selectedCharactersById(newGameContainer.getAsList(newGameContainer.characters))
        monsters = repositoryMonsters.selectedMonstersWithTypeById(newGameContainer.getAsList(newGameContainer.monsters))
        scenario = repositoryScenario.selectedScenario(newGameContainer.scenario)
    }

    fun writeHistoryEntry(newGameContainer: NewGameContainer, context: Context){
        repositoryHistory.writeHistoryEntry(newGameContainer.scenario, context)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}