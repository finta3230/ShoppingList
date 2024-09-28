package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveShopItemUseCase
import com.example.shoppinglist.domain.ShopItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ShopListRepositoryImpl) : ViewModel() {

    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)

    private val _shopList = MutableLiveData<List<ShopItem>>()
    val shopList: LiveData<List<ShopItem>> = _shopList

    init {
        updateShopItemList()
    }

    fun changeShopItemState(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem.copy(enabled = !shopItem.enabled))
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
        updateShopItemList()
    }

    fun removeShopItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val shopItemList = _shopList.value
            shopItemList?.let { removeShopItemUseCase.removeShopItem(it.last()) }
            updateShopItemList()

        }
    }
// TODO() вызывать view для добавления элемента
    fun addShopItem() {
        viewModelScope.launch(Dispatchers.IO) {
            addShopItemUseCase.addShopItem(ShopItem("Some name", 0, true))
            updateShopItemList()

        }
    }

    private fun updateShopItemList() {
        viewModelScope.launch(Dispatchers.IO) {
            _shopList.postValue(getShopListUseCase.getShopList())
            updateShopItemList()

        }
    }

}

class MainViewModelFactory(private val repositoryImpl: ShopListRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(
            repositoryImpl
        ) as T
        return super.create(modelClass)
    }
}