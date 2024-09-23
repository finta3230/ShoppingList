package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

//Класс который будет реализовывать интрфейс в domain пакете. Добавляют к имени -Impl
//Data слой отвечает за работу с данными. Он предоставляет конкретную реализацию репозитория и зависит от Domain слоя и знает о нём всё


class ShopListRepositoryImpl(private val dao : ShopListDao) : ShopListRepository {

    private var autoIncrementId = 0

    override fun addShopItem(item: ShopItem) {
        if (item.id == ShopItem.UNDEFINED_ID) item.id = autoIncrementId++
        dao.addShopItem(item)
    }

    override fun editShopItem(shopItem: ShopItem) {
        dao.editShopItem(shopItem)
    }

    override fun getShopItem(id: Int): ShopItem {
        return dao.getShopItem(id)
    }
// Передаём именно копию списка
    override fun getShopList(): List<ShopItem> {
        return dao.getShopList()
    }

    override fun removeShopItem(shopItem: ShopItem) {
        dao.removeShopItem(shopItem)
    }

}
