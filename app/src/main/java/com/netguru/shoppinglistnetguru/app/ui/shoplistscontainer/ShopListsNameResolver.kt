package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer

import com.netguru.shoppinglistnetguru.R

object ShopListsNameResolver {
    fun getItemNameRes(position: Int): Int {
        return when (position) {
            0 -> R.string.tv_tab_list_active
            1 -> R.string.tv_tab_list_archived
            else -> -1
        }
    }
}