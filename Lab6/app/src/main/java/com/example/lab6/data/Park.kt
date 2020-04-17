package com.example.lab6.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse (
    val data: Set<Park>,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class Park (
    val name: String,
    val states: String,
    val description: String,
    val directionsUrl: String,
    val parkCode: String
)
