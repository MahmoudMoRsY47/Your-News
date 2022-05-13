package com.example.yournews.api


import com.example.yournews.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitAPI {
    companion object{

        private val retrofite = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofite.create(NewsAPI::class.java)


    }
}