package com.wik4apps.retrotutorial.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wik4apps.retrotutorial.service.ProductApi
import com.wik4apps.retrotutorial.service.ProductService
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private var productsService : ProductApi = ProductService.createService(ProductApi::class.java)
    private val _products = MutableLiveData<List<Product>>()
    val products: MutableLiveData<List<Product>>
        get() = _products

    fun getProducts(){
        viewModelScope.launch {
            try {
                val response = productsService.getProducts()
                val listResult = response.body()!!
                if (response.isSuccessful) {
                    _products.value = listResult
                    Log.d("ProductViewModel", "getProducts: ${listResult.size}")
                }
                else {
                    _products.value = listOf()
                    Log.d("ProductViewModel", "getProducts: ${response.message()}")
                }
            } catch (e: Exception) {
                _products.value = listOf()
                Log.e("ProductViewModel", e.message.toString())
            }
        }
    }

}