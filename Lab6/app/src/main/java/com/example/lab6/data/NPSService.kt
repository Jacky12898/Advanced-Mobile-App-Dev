package com.example.lab6.data

import com.example.lab6.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface NPSService {
    @GET
    fun searchParks(@Url url: String): Call<SearchResponse>

    //@GET
    //fun parkDetails(@Url parkCode: String): Call<ParkDetails>
}