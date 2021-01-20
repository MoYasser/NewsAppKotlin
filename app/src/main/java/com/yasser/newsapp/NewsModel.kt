package com.yasser.newsapp

import com.google.gson.annotations.SerializedName

data class NewsModel (
    @SerializedName("author") var newsAuthor: String,
    @SerializedName("description") var newsDescription: String,
    @SerializedName("title") var headLineTitle: String,
    @SerializedName("urlToImage") var headLineThumbNail: String,
    @SerializedName("url") var visit: String,
    @SerializedName("content") var newsContent: String
)

class Source(
    val id: String?,
    val name: String
)