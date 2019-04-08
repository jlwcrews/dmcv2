package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jlwcrews.dmcv2.db.DmcDatabase
import no.jlwcrews.dmcv2.db.entities.Expansion
import no.jlwcrews.dmcv2.db.repos.ExpansionRepo

import kotlin.coroutines.CoroutineContext

class ExpansionViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ExpansionRepo
    var allExpansions: LiveData<List<Expansion>>

    init {
        val expansionDao = DmcDatabase.getDatabase(application, scope).expansionDao()
        repository = ExpansionRepo(expansionDao)
        allExpansions = repository.allExpansions
    }

    fun insert(expansion: Expansion) = scope.launch(Dispatchers.IO) {
        repository.insert(expansion)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}