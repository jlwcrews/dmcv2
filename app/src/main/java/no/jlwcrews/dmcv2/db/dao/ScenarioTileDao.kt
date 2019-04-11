package no.jlwcrews.dmc.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jlwcrews.dmc.db.entities.ScenarioTile

@Dao
interface ScenarioTileDao{
    @Query("select * from dmc_scenario_tiles")
    fun getAllScenarioTiles(): LiveData<List<ScenarioTile>>

    @Query("select * from dmc_scenario_tiles where scenario_id=:scenarioId")
    fun getScenarioTiles(scenarioId: Int): LiveData<List<ScenarioTile>>

    @Query("delete from dmc_scenario_tiles")
    fun deleteAll()

    @Insert
    fun insert(vararg scenarioTile: ScenarioTile)

    @Delete
    fun delete(vararg scenarioTile: ScenarioTile)

    @Update
    fun update(vararg scenarioTile: ScenarioTile)
}