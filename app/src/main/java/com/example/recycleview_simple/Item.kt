package com.example.recycleview_simple

import android.R
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

enum class ItemCategories{
    ALL,
    APPETISER,
    BREAKFAST,
    DESSERT,
    DRINK,
    MAIN,
    SIDE,
    SNACK,
}

@Parcelize
data class Item(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val category: ItemCategories = ItemCategories.ALL,
    val description: String = "",
    val imageSrc: Int = R.drawable.star_on,
    val isFavourite: Boolean = false
): Parcelable

