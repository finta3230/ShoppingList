package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.ShoppingListApplication

//Presentation слой отвечает за отображение данных и взаимодействие с пользователем.
//Методы бизнес логики вызываются из UseCase
//С domain слоем взаимодействует ViewModel. Presentation знает о Domain но ничего о Data слое.

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShoppingListApplication).repository)
    }
    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.shopList.observe(this){
            Log.d("Main Activity", it.toString())
            if (count == 0) {
                count++
                val item = it[0]
                viewModel.changeShopItemState(item)
            }
        }


    }
}