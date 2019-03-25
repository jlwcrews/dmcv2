package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.PlayerCharacter

@Dao
interface CharacterDao{

    @Query("select * from dmc_characters order by character_id asc")
    fun getAllCharacters(): LiveData<List<PlayerCharacter>>

    @Query("delete from dmc_characters")
    fun deleteAll()

    @Query("select * from dmc_characters where character_expansion_id=:expansionId")
    fun getCharactersByExpansion(expansionId: Int): LiveData<List<PlayerCharacter>>

    @Query("select * from dmc_characters where character_id = :characterId")
    fun getCharacter(characterId: Int): PlayerCharacter

    @Insert
    fun insert(vararg playerCharacters: PlayerCharacter)

    @Delete
    fun delete(vararg playerCharacters: PlayerCharacter)

    @Update
    fun update(vararg playerCharacters: PlayerCharacter)
}