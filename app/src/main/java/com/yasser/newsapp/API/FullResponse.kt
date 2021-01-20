package com.yasser.newsapp.API



import com.google.gson.annotations.SerializedName
import com.yasser.newsapp.NewsModel

data class FullResponse(
    @SerializedName("articles") val articles: ArrayList<NewsModel>,
    @SerializedName("stat") val stat: String
)