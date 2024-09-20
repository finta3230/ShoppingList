package com.example.shoppinglist.domain


//Domain пакет содержит всю бизнес логику приложения и не зависит ни от чего.
//Для реализации каждого действия, прописанного в бизнес процессе, и сохранения принципа чистой
//архитектуры создают на каждый метод отдельный класс - Interactor или UseCase.

data class ShopItem(
    val name : String,
    val count : Int,
    val enabled : Boolean,
    var id : Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = -1
    }

}

