package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import android.view.View
import kotlinx.coroutines.*
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmc.db.repo.CharacterRepo
import no.jlwcrews.dmc.db.repo.MonsterWithTypeRepo
import no.jlwcrews.dmc.db.repo.ScenarioRepo
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.models.MonsterActionWithAction
import no.jlwcrews.dmcv2.db.models.MonsterWithType
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.db.repos.MonsterActionsWithActionRepo
import kotlin.coroutines.CoroutineContext

class SummaryViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repositoryCharacter: CharacterRepo
    private val repositoryMonsters: MonsterWithTypeRepo
    private val repositoryMonsterActions: MonsterActionsWithActionRepo
    private val repositoryScenario: ScenarioRepo

    lateinit var characters: LiveData<List<PlayerCharacter>>
    lateinit var monsters: LiveData<List<MonsterWithType>>
    lateinit var monsterActions: LiveData<List<MonsterActionWithAction>>
    lateinit var scenario: LiveData<Scenario>

    init {
        val characterDao = DmcDatabase.getDatabase(application, scope).characterDao()
        val monsterDao = DmcDatabase.getDatabase(application, scope).monsterWithTypeDao()
        val monsterActionsDao = DmcDatabase.getDatabase(application, scope).monsterActionsWithActionDao()
        val scenarioDao = DmcDatabase.getDatabase(application, scope).scenarioDao()

        repositoryCharacter = CharacterRepo(characterDao)
        repositoryMonsters = MonsterWithTypeRepo(monsterDao)
        repositoryMonsterActions = MonsterActionsWithActionRepo(monsterActionsDao)
        repositoryScenario = ScenarioRepo(scenarioDao)
    }

    fun initViewModel(newGameContainer: NewGameContainer){
        characters = repositoryCharacter.selectedCharactersById(newGameContainer.getAsList(newGameContainer.characters))
        monsters = repositoryMonsters.selectedMonstersWithTypeById(newGameContainer.getAsList(newGameContainer.monsters))
        monsterActions = repositoryMonsterActions.getMonsterActionsWithActionByMonsterId(newGameContainer.getAsList(newGameContainer.monsters))
        scenario = repositoryScenario.selectedScenario(newGameContainer.scenario)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}