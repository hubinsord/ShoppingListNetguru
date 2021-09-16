package com.netguru.shoppinglistnetguru.app.data.model

data class ShoppingItem (
    val name: String,
    var quantity: Int,
    var isItemChecked: Boolean = false
        )