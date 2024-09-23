package com.example.shoppinglist.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.domain.ShopItem

@Database(entities = [ShopItem::class], version = 1, exportSchema = false)
abstract class ShopListDatabase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {

        private const val DB_NAME = "shopList.db"

        @Volatile
        private var INSTANCE: ShopListDatabase? = null

        fun getDatabase(context: Context): ShopListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopListDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}