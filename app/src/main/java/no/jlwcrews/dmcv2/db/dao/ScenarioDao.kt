package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.Scenario

@Dao
interface ScenarioDao{
    @Query("select * from dmc_scenarios")
    fun getAllScenarios(): LiveData<List<Scenario>>

    @Query("delete from dmc_scenarios")
    fun deleteAll()

    @Query("select * from dmc_scenarios where scenario_expansion_id IN (:expansionId)")
    fun getScenariosByExpansion(expansionId: List<Int>): LiveData<List<Scenario>>

    @Query("select * from dmc_scenarios where scenario_id IN (:scenarioId)")
    fun getScenariosById(scenarioId: List<Int>): LiveData<List<Scenario>>

    @Insert
    fun insert(vararg scenario: Scenario)

    @Delete
    fun delete(vararg scenario: Scenario)

    @Update
    fun update(vararg scenario: Scenario)
}