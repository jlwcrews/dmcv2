package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.entities.Monster
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmc.db.repo.CharacterRepo
import no.jlwcrews.dmc.db.repo.MonsterRepo
import no.jlwcrews.dmc.db.repo.MonsterWithTypeRepo
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.models.MonsterWithType

import kotlin.coroutines.CoroutineContext

class MonsterViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MonsterWithTypeRepo
    lateinit var monsters: LiveData<List<MonsterWithType>>

    init {
        val monsterWithTypeDao = DmcDatabase.getDatabase(application, scope).monsterWithTypeDao()
        repository = MonsterWithTypeRepo(monsterWithTypeDao)
    }

    fun initMonsters(expansions: List<Int>){
        monsters = repository.selectedMonstersWithType(expansions)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}