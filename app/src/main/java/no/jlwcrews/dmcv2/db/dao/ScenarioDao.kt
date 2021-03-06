package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.Scenario

@Dao
interface ScenarioDao{
    @Query("select * from dmc_scenarios")
    fun getAllScenarios(): LiveData<List<Scenario>>

    @Query("delete from dmc_scenarios")
    fun deleteAll()

    @Query("select * from dmc_scenarios where scenario_expansion_id IN (:expansionId)")
    fun getScenariosByExpansion(expansionId: List<Int>): LiveData<List<Scenario>>

    @Query("select * from dmc_scenarios where scenario_id = :scenarioId")
    fun getScenarioById(scenarioId: Int): LiveData<Scenario>

    @Insert
    fun insert(vararg scenario: Scenario)

    @Delete
    fun delete(vararg scenario: Scenario)

    @Update
    fun update(vararg scenario: Scenario)
}