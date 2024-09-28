package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.ShoppingListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

//Presentation слой отвечает за отображение данных и взаимодействие с пользователем.
//Методы бизнес логики вызываются из UseCase
//С domain слоем взаимодействует ViewModel. Presentation знает о Domain но ничего о Data слое.

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShoppingListApplication).repository)
    }
    private lateinit var shopListAdapter : ShopListAdapter


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
        viewModel.shopList.observe(this){
            shopListAdapter.submitList(it)
        }

        val addShopItemButton = findViewById<FloatingActionButton>(R.id.addShopItemButton).setOnClickListener{
            viewModel.addShopItem()
        }

    }

    private fun setUpRecyclerView(){
        val shopListRecyclerView : RecyclerView = findViewById(R.id.shopItemListRecyclerView)
        with(shopListRecyclerView){
            shopListAdapter = ShopListAdapter()
            this.adapter = shopListAdapter
        }
    }
}