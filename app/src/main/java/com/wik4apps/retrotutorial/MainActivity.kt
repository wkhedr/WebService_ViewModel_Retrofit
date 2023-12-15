package com.wik4apps.retrotutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.wik4apps.retrotutorial.databinding.ActivityMainBinding
import com.wik4apps.retrotutorial.model.Product
import com.wik4apps.retrotutorial.model.ProductViewModel
import com.wik4apps.retrotutorial.service.ProductApi
import com.wik4apps.retrotutorial.service.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1)
        viewModel.getProducts()
        //val productsService =  ProductService.createService(ProductApi::class.java)
        viewModel.products.observe(this) {
            adapter.addAll(it)
            binding.listview.adapter = adapter
            binding.listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(this@MainActivity, DetalisActivity::class.java)
                intent.putExtra("productID", it[position].id)
                startActivity(intent)
            }
        }

    }
}


/*
CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = productsService.getProducts()
                val products = response.body()!!
                adapter.addAll(products)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main){
                        binding.listview.adapter = adapter
                        binding.listview.setOnItemClickListener { parent, view, position, id ->
                            val intent = Intent(this@MainActivity, DetalisActivity::class.java)
                            intent.putExtra("productID", products[position].id)
                            startActivity(intent)
                        }
                    }
                } else {
                    Log.d("MainActivity", "onCreate: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("MainActivity", e.message.toString())
            }
        }
 */