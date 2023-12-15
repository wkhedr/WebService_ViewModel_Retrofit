package com.wik4apps.retrotutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coil.load
import com.wik4apps.retrotutorial.databinding.ActivityDetalisBinding
import com.wik4apps.retrotutorial.service.ProductApi
import com.wik4apps.retrotutorial.service.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("productID", 0)
        val productsService =  ProductService.createService(ProductApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = productsService.getProductById(id)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main){
                        val product = response.body()!!
                        binding.imageView.load(product.thumbnail)
                        binding.textViewId.text = product.id.toString()
                        binding.textViewTitle.text = product.title
                        binding.textViewDesc.text = product.description
                        binding.textViewRate.text = product.rating.toString()
                    }
                } else {
                    Log.d("DetalisActivity", "onCreate: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("DetalisActivity", e.message.toString())
            }
        }
    }
}