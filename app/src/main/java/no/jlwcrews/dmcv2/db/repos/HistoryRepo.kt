package no.jlwcrews.dmc.db.repo

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.dao.HistoryDao
import no.jlwcrews.dmc.db.entities.History


class HistoryRepo(private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()
    lateinit var context: Context
    lateinit var history: History

    @WorkerThread
    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    fun writeHistoryEntry(scenarioId: Int, context: Context){
        this.context = context
        history = History(0, "", "", scenarioId)
        getTimeFromApi()

    }

    fun getTimeFromApi(){
        var historyDate: History = history
        lateinit var apiTime: ApiTime
        val url = "http://worldtimeapi.org/api/ip"
        val client = AsyncHttpClient()
        val request = client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val gson = GsonBuilder().create()
                historyDate.historyDate = gson.fromJson(String(responseBody!!), ApiTime::class.java).datetime
                getLocationData()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {}
        })
    }

    fun getLocationData(){
        //fuck location data
        GlobalScope.launch(Dispatchers.IO) {
            insert(history)
        }
    }
 }

data class ApiTime (

    @SerializedName("week_number") val week_number : Int,
    @SerializedName("utc_offset") val utc_offset : String,
    @SerializedName("unixtime") val unixtime : Int,
    @SerializedName("timezone") val timezone : String,
    @SerializedName("dst_until") val dst_until : String,
    @SerializedName("dst_from") val dst_from : String,
    @SerializedName("dst") val dst : Boolean,
    @SerializedName("day_of_year") val day_of_year : Int,
    @SerializedName("day_of_week") val day_of_week : Int,
    @SerializedName("datetime") val datetime : String,
    @SerializedName("abbreviation") val abbreviation : String
)