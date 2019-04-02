package no.jlwcrews.dmc.db.repo

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jlwcrews.dmc.db.dao.CharacterDao
import no.jlwcrews.dmc.db.entities.PlayerCharacter

class CharacterRepo(private val characterDao: CharacterDao) {

    val allCharacters: LiveData<List<PlayerCharacter>> = characterDao.getAllCharacters()

    fun selectedCharacters(expansionId: List<Int>): LiveData<List<PlayerCharacter>>{
        return characterDao.getCharactersByExpansion(expansionId)
    }

    @WorkerThread
    suspend fun insert(playerCharacter: PlayerCharacter) {
        characterDao.insert(playerCharacter)
    }
}