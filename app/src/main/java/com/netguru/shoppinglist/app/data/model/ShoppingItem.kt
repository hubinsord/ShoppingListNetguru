package com.netguru.shoppinglist.app.data.model

data class ShoppingItem (
    val name: String,
    var quantity: Int,
    var isItemChecked: Boolean = false
        )