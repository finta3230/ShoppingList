package com.example.shoppinglist

import android.app.Application
import androidx.room.Room
import com.example.shoppinglist.data.ShopListDatabase
import com.example.shoppinglist.data.ShopListRepositoryImpl

class ShoppingListApplication : Application() {

    val database by lazy { ShopListDatabase.getDatabase(this) }
    val repository by lazy { ShopListRepositoryImpl(database.shopListDao()) }

}

