package com.example.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.domain.ShopItem

@Database(entities = [ShopItem::class], version = 1, exportSchema = false)
abstract class ShopListDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "shopList.db"
    }

    private lateinit var applicationContext: Application

    fun init(application: Application) {
        applicationContext = application
    }
    
    val instance: ShopListDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ShopListDatabase::class.java,
            DB_NAME
        ).build()
    }


    abstract fun shopListDao(): ShopListDao
}