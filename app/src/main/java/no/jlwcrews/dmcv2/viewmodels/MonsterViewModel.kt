package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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