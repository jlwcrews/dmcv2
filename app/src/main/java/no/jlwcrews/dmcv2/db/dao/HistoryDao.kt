package no.jlwcrews.dmc.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import no.jlwcrews.dmc.db.entities.History

@Dao
interface HistoryDao{
    @Query("select * from dmc_history")
    fun getAllHistory(): LiveData<List<History>>

    @Query("select * from dmc_history where history_id=:historyId")
    fun getHistory(historyId: Int): History

    @Query("delete from dmc_history")
    fun deleteAllHistory()

    @Insert
    fun insert(vararg history: History)

    @Delete
    fun delete(vararg history: History)

    @Update
    fun update(vararg history: History)
}