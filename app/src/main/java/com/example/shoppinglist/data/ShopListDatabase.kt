package com.example.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.domain.ShopItem

@Database(entities = [ShopItem::class], version = 1)
abstract class ShopListDatabase : RoomDatabase() {

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    private val shopListDatabase: ShopListDatabase by lazy {
        Room.databaseBuilder(application, ShopListDatabase::class.java, DB_NAME)
            .createFromAsset("room_article.db")
            .build()
    }

    companion object {
        private const val DB_NAME = "shopList.db"
    }

    abstract fun shopListDao() : ShopListDao
}