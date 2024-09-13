package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

//Класс который будет реализовывать интрфейс в domain пакете. Добавляют к имени -Impl
//Data слой отвечает за работу с данными. Он предоставляет конкретную реализацию репозитория и зависит от Domain слоя и знает о нём всё

//TODO - вместо переменной создать БД.

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItemDb>()

//    private var autoIncrementId = 0

//    init {
//        for (i in 0 until 10){
//            val item = ShopItem("Name $i", i, true)
//            addShopItem(item)
//        }
//    }

    override fun addShopItem(item: ShopItemDb) {
//        if (item.id == ShopItem.UNDEFINED_ID) item.id = autoIncrementId++
        shopList.add(item)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItemDb) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(id: Int): ShopItem? {
        return shopList.find { it.id == id }
    }
// Передаём именно копию списка
    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun removeShopItem(shopItem: ShopItemDb) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}