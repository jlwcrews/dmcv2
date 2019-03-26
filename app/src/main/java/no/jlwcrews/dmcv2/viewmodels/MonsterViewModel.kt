package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.entities.Monster
import no.jlwcrews.dmc.db.repo.MonsterRepo
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.entities.Expansion
import no.jlwcrews.dmcv2.db.repos.ExpansionRepo

import kotlin.coroutines.CoroutineContext

class MonsterViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MonsterRepo
    lateinit var allMonsters: LiveData<List<Monster>>

    init {
        val monsterDao = DmcDatabase.getDatabase(application, scope).monsterDao()
        repository = MonsterRepo(monsterDao)
        allMonsters = repository.allMonsters
    }

    fun insert(monster: Monster) = scope.launch(Dispatchers.IO) {
        repository.insert(monster)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}