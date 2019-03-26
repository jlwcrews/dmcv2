package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.Action

@Dao
interface ActionDao{

    @Query("select * from dmc_actions")
    fun getAllActions(): LiveData<List<Action>>

    @Query("select * from dmc_actions where action_id = :actionId")
    fun getAction(actionId: Int): Action

    @Query("select * from dmc_actions where action_name = :actionName")
    fun getActionByName(actionName: String): Action

    @Query("delete from dmc_actions")
    fun deleteAll()

    @Insert
    fun insert(vararg action: Action)

    @Delete
    fun delete(vararg action: Action)

    @Update
    fun update(vararg action: Action)

}