package no.jlwcrews.dmcv2.db.repos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils.substring
import androidx.annotation.WorkerThread
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationServices
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
import no.jlwcrews.dmcv2.views.ui.MainActivity


class HistoryRepo(private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()
    lateinit var context: Context
    private val REQUEST_PERMISSION_LOCATION = 10

    @WorkerThread
    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    fun writeHistoryEntry(scenarioId: Int, context: Context){
        this.context = context
        var history = History(0, "", "", scenarioId)
        getTimeFromApi(history)

    }

    private fun getTimeFromApi(history: History){
        lateinit var apiTime: ApiTime
        val url = "http://worldtimeapi.org/api/ip"
        val client = AsyncHttpClient()
        val request = client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                val gson = GsonBuilder().create()
                history.historyDate = substring(gson.fromJson(String(responseBody!!), ApiTime::class.java).datetime, 0, 10)
                getLocationData(history)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {}
        })
    }

    fun getLocationData(history: History){
        var newHistory = history
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if(!checkPermissionForLocation(context)){
                println("Permission not granted")
                return
            }
        }
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            val locationString = "${it.latitude} ${it.latitude}"
            newHistory.historyLocation = locationString
            GlobalScope.launch(Dispatchers.IO) {
                insert(newHistory)

        }

        }
    }

    private fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    MainActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION)
                false
            }
        } else {
            true
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
 }

