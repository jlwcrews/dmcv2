package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.ScenarioCard

@Dao
interface ScenarioCardDao{
    @Query("select * from dmc_scenario_cards")
    fun getAllScenarioCards(): LiveData<List<ScenarioCard>>

    @Query("delete from dmc_scenario_cards")
    fun deleteAll()

    @Query("select * from dmc_scenario_cards where scenario_card_id = :scenarioCardId")
    fun getScenarioCard(scenarioCardId: Int): ScenarioCard

    @Insert
    fun insert(vararg scenarioCard: ScenarioCard)

    @Delete
    fun delete(vararg scenarioCard: ScenarioCard)

    @Update
    fun update(vararg scenarioCard: ScenarioCard)
}