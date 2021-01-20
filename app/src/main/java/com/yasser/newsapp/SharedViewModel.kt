package com.yasser.newsapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yasser.newsapp.API.ApiClient
import com.yasser.newsapp.Database.DataModel

class SharedViewModel: ViewModel() {

    private val headlinesMutLivData : MutableLiveData<ArrayList<NewsModel>> = MutableLiveData()
    private val headlinesMutLivDataStatus : MutableLiveData<String> = MutableLiveData()
    private val savedMutLivData : MutableLiveData<DataModel> = MutableLiveData()

    private val headlineMutLivData : MutableLiveData<NewsModel> = MutableLiveData()


    fun getHeadlines(): LiveData<ArrayList<NewsModel>>{
        return headlinesMutLivData
    }

    fun getHeadline(): LiveData<NewsModel>{
        return headlineMutLivData
    }

    fun clickedHeadline(headline: NewsModel){
        headlineMutLivData.value = headline
    }


    fun getSaved(): LiveData<DataModel>{
        return savedMutLivData
    }

    fun clickedSaved(headline: DataModel){
        savedMutLivData.value = headline
    }

    fun getArticles(page: Int){
        ApiClient.topHeadlinesResponse(page, ::shareData, ::noData)
    }

    private fun noData(reason: String) {
        headlinesMutLivDataStatus.value = reason
    }

    private fun shareData(headlines: ArrayList<NewsModel>) {
        headlinesMutLivDataStatus.value = "success"
        headlinesMutLivData.value = headlines
    }

}
