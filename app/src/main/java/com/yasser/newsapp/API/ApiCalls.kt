package com.yasser.newsapp.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("v2/top-headlines")

    fun topHeadlines(@Query("apiKey") apiKey: String = "API_KEY", @Query("country") country: String = "us", @Query("page") currentPage: Int = 1): Call<FullResponse>
}