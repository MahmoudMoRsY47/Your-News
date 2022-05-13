package com.example.yournews.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yournews.model.NewsResponse
import com.example.yournews.repository.NewsRepository
import com.example.yournews.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (): ViewModel() {
    val newsRepository= NewsRepository()

    val news:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    val search:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getBreakingNews()
    }
    fun getBreakingNews()=viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews()
        news.postValue(handleBreakingNewsResponse(response))
    }


    fun searchNews(searchQuesry:String)=viewModelScope.launch {
        search.postValue(Resource.Loading())
        val response = newsRepository.search(searchQuesry)
        search.postValue(handleSearch(response))
    }

    private fun handleBreakingNewsResponse(response :Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearch(response :Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}