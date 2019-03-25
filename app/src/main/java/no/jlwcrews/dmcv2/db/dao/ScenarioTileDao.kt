package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.ScenarioTile

@Dao
interface ScenarioTileDao{
    @Query("select * from dmc_scenario_tiles")
    fun getAllScenarioTiles(): LiveData<List<ScenarioTile>>

    @Query("select * from dmc_scenario_tiles where scenario_id=:scenarioId")
    fun getScenarioTiles(scenarioId: Int): LiveData<List<ScenarioTile>>

    @Insert
    fun insert(vararg scenarioTile: ScenarioTile)

    @Delete
    fun delete(vararg scenarioTile: ScenarioTile)

    @Update
    fun update(vararg scenarioTile: ScenarioTile)
}