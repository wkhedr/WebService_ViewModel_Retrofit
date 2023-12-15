package com.wik4apps.retrotutorial.service

import com.wik4apps.retrotutorial.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id:Int):Response<Product>
}