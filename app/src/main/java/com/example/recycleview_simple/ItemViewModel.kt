package com.example.recycleview_simple

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>(
        listOf(
            Item("1", "Caldo Verde", ItemCategories.SIDE, "Green soup"),
            Item("2", "Salisbury Steak", ItemCategories.MAIN, "Fake steak"),
            Item("3", "Pho", ItemCategories.MAIN, "Beef noodle soup"),
            Item("4", "Tuna Mornay", ItemCategories.MAIN, "Tuna casserole"),
            Item("5", "Spaghetti Bolognese", ItemCategories.MAIN,"Meat, spaghetti and tomato based sauce"),
            Item("6", "Ice Cream", ItemCategories.DESSERT,"Frozen milk dessert treat"),
            Item("7","Bacon and Waffles", ItemCategories.BREAKFAST, "Waffles loaded with bacon and maple syrup"),
            Item("8", "Water", ItemCategories.DRINK, "Glass of water"),
            Item("9", "Arancini", ItemCategories.APPETISER, "Deep fried Italian balls"),
            Item("10","Toast", ItemCategories.SNACK, "Yummy")
        )
    )

    val items: LiveData<List<Item>> = _items

    fun toggleFav(item: Item) {
        viewModelScope.launch {
            val currentList = _items.value.orEmpty()
            val updatedList = currentList.map {
                if (it.id == item.id) it.copy(isFavourite = !it.isFavourite)
                else it
            }
            _items.value = updatedList
        }
    }
}