package com.wik4apps.retrotutorial.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ProductService {
    private val base_url = "http://192.168.1.10:3000/"
    private fun getInstance(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    fun <T>createService(serviceType: Class<T>): T {
        return getInstance().create(serviceType)
    }

}