package com.example.film_app

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


fun getPopular() : String{
    val client = OkHttpClient()
    val request: Request = Request.Builder()
        .url("https://kinopoiskapiunofficial.tech/api/v2.2/films/top")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("X-API-KEY", "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
        .addHeader("type", "TOP_100_POPULAR_FILMS")
        .build()
    var body_response: String
    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        body_response = response.body!!.string()
    }
    return body_response
//    val jsonData = responses.body!!.toString()
}