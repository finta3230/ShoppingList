package com.example.shoppinglist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


//Domain пакет содержит всю бизнес логику приложения и не зависит ни от чего.
//Для реализации каждого действия, прописанного в бизнес процессе, и сохранения принципа чистой
//архитектуры создают на каждый метод отдельный класс - Interactor или UseCase.

@Entity(tableName = "shop_items")
data class ShopItem(
    val name : String,
    val count : Int,
    val enabled : Boolean,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}

