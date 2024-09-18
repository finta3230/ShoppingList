package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

//Presentation слой отвечает за отображение данных и взаимодействие с пользователем.
//Методы бизнес логики вызываются из UseCase
//С domain слоем взаимодействует ViewModel. Presentation знает о Domain но ничего о Data слое.

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            adapter.shopList = it
        }

    }

    private fun setUpRecyclerView () {
        val shopListRecyclerView = findViewById<RecyclerView>(R.id.shopListRecyclerView)
        adapter = ShopListAdapter()
        shopListRecyclerView.adapter = adapter
        shopListRecyclerView
            .recycledViewPool
            .setMaxRecycledViews(ShopListAdapter.IS_ACTIVE, ShopListAdapter.MAX_POOL_SIZE)
        shopListRecyclerView
            .recycledViewPool
            .setMaxRecycledViews(ShopListAdapter.IS_NON_ACTIVE, ShopListAdapter.MAX_POOL_SIZE)
    }
}