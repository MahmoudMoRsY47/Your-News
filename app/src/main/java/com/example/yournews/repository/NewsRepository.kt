package com.example.yournews.repository

import com.example.yournews.api.RetrofitAPI


class NewsRepository() {

    suspend fun getBreakingNews()= RetrofitAPI.api.getBreakingNews()
    suspend fun search(searchQuery : String)= RetrofitAPI.api.searchForNews(searchQuery)

}