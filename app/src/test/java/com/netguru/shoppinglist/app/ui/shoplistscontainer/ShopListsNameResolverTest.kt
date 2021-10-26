package com.netguru.shoppinglist.app.ui.shoplistscontainer

import com.netguru.shoppinglist.R
import org.junit.Assert.*

import org.junit.Test


class ShopListsNameResolverTest {

    @Test
    fun `get positioned as active fragment name returns correct resource`() {
        val res = ShopListsNameResolver.getItemNameRes(0)
        assertEquals(res, R.string.tv_tab_list_active)
    }

    @Test
    fun `get positioned as archive fragment name returns correct resource`() {
        val res = ShopListsNameResolver.getItemNameRes(1)
        assertEquals(res, R.string.tv_tab_list_archived)
    }

    @Test
    fun `get other fragment name returns correct resource`() {
        val res = ShopListsNameResolver.getItemNameRes(1337)
        assertEquals(res, -1)
    }
}