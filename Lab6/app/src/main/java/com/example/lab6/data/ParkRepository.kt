package com.example.lab6.data

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab6.API_KEY
import com.example.lab6.BASE_URL
import com.example.lab6.LOG_TAG
import com.example.lab6.utils.NetworkHelper
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ParkRepository(val app: Application) {
    private val listType = Types.newParameterizedType(List::class.java, Park::class.java)

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private var service: NPSService

    init {
        service = retrofit.create(NPSService::class.java)
    }

    val searchTermEntered =  Observer<String> {
        CoroutineScope(Dispatchers.IO).launch {
            getParkList(it)
        }
    }

    val parkData = MutableLiveData<List<Park>>()

    @WorkerThread
    private suspend fun getParkList(searchTerm: String) {
        Log.i(LOG_TAG, searchTerm)
        if(NetworkHelper.networkConnected(app)) {
            val url = "api/v1/parks?stateCode=${searchTerm}&api_key=$API_KEY"
            val response = service.searchParks(url).execute()
            if(response.body() != null) {
                val responseBody = response.body()
                Log.i(LOG_TAG, responseBody?.data?.toString())
                parkData.postValue(responseBody?.data?.toList())
            } else {
                Log.e(LOG_TAG, "Could not find parks. Error code: ${response.code()}")
            }
        }
    }

    val parkSelectedObserver =  Observer<Park> {
        CoroutineScope(Dispatchers.IO).launch {
            getParkDetails(it)
        }
    }

    val parkDetails = MutableLiveData<ParkDetails>()

    @WorkerThread
    private suspend fun getParkDetails(forPark: Park) {
        if(NetworkHelper.networkConnected(app)) {
            val parkCode = "api/v1/parks?parkCode=${forPark.parkCode}&api_key=${API_KEY}"
            val response = service.parkDetails(parkCode).execute()
            if(response.body() != null) {
                parkDetails.postValue(response.body())
            } else {
                Log.e(LOG_TAG, "Could not find parks for ${forPark.parkCode}. Error code ${response.code()}")
            }
        }
    }
}