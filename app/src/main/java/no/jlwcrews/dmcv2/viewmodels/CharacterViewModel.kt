package no.jlwcrews.dmcv2.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmc.db.repo.CharacterRepo
import no.jlwcrews.dmcv2.db.DmcDatabase

import kotlin.coroutines.CoroutineContext

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CharacterRepo
    lateinit var characters: LiveData<List<PlayerCharacter>>

    init {
        val characterDao = DmcDatabase.getDatabase(application, scope).characterDao()
        repository = CharacterRepo(characterDao)
    }

    fun insert(character: PlayerCharacter) = scope.launch(Dispatchers.IO) {
        repository.insert(character)
    }

    fun initCharacters(expansions: List<Int>){
        characters = repository.selectedCharacters(expansions)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}