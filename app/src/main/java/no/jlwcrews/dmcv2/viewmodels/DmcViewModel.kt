package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import no.jlwcrews.dmc.db.entities.History
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmc.db.repo.CharacterRepo
import no.jlwcrews.dmc.db.repo.HistoryRepo
import no.jlwcrews.dmc.db.repo.MonsterWithTypeRepo
import no.jlwcrews.dmc.db.repo.ScenarioRepo
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.entities.Expansion
import no.jlwcrews.dmcv2.db.models.MonsterWithType
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.db.repos.ExpansionRepo
import kotlin.coroutines.CoroutineContext

class DmcViewModel(application: Application): AndroidViewModel(application){

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repositoryCharacter: CharacterRepo
    private val repositoryMonsters: MonsterWithTypeRepo
    private val repositoryScenario: ScenarioRepo
    private val repositoryExpansion: ExpansionRepo
    private val repositoryHistory: HistoryRepo

    lateinit var characters: LiveData<List<PlayerCharacter>>
    lateinit var monsters: LiveData<List<MonsterWithType>>
    lateinit var scenarios: LiveData<List<Scenario>>
    lateinit var scenario: LiveData<Scenario>
    var history: LiveData<List<History>>
    var expansions: LiveData<List<Expansion>>

    init {
        val characterDao = DmcDatabase.getDatabase(application, scope).characterDao()
        val monsterDao = DmcDatabase.getDatabase(application, scope).monsterWithTypeDao()
        val scenarioDao = DmcDatabase.getDatabase(application, scope).scenarioDao()
        val expansionDao = DmcDatabase.getDatabase(application, scope).expansionDao()
        val historyDao = DmcDatabase.getDatabase(application, scope).historyDao()

        repositoryCharacter = CharacterRepo(characterDao)
        repositoryMonsters = MonsterWithTypeRepo(monsterDao)
        repositoryScenario = ScenarioRepo(scenarioDao)
        repositoryExpansion = ExpansionRepo(expansionDao)
        repositoryHistory = HistoryRepo(historyDao)
        history = repositoryHistory.allHistory
        expansions = repositoryExpansion.allExpansions
    }

    fun initViewModel(newGameContainer: NewGameContainer){
        characters = repositoryCharacter.selectedCharacters(newGameContainer.getAsList(newGameContainer.expansions))
        monsters = repositoryMonsters.selectedMonstersWithType(newGameContainer.getAsList(newGameContainer.expansions))
        scenarios = repositoryScenario.selectedScenarios(newGameContainer.getAsList(newGameContainer.expansions))
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}