package com.yasser.newsapp.API

import com.yasser.newsapp.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val baseURL = "https://newsapi.org/"
    private val service: ApiCalls
    private var retrofit: Retrofit? = null

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ApiCalls::class.java)
    }

    fun topHeadlinesResponse(
        currentPage: Int,
        noError: (headlines: ArrayList<NewsModel>) -> Unit,
        error: (reason: String) -> Unit
    ) {
        service.topHeadlines(currentPage = currentPage).enqueue(object : Callback<FullResponse?> {
                override fun onFailure(call: Call<FullResponse?>, t: Throwable) {
                    error.invoke("!!! failed to hit api --- page $currentPage --- error is $t")
                }

                override fun onResponse(
                    call: Call<FullResponse?>,
                    response: Response<FullResponse?>
                ) {
                    if (response.body() == null) {
                        error.invoke("!!! response.body is NULL ${response.body()?.stat}")
                    } else {
                        noError.invoke(response.body()!!.articles)
                    }
                }

            })
    }

}