package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active.ActiveShopListsFragment
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.archived.ArchivedShopListsFragment
import java.util.ArrayList

class ShopListsAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = arrayListOf(
        ActiveShopListsFragment.newInstance(),
        ArchivedShopListsFragment.newInstance()
    )

    fun getItemNameRes(position: Int): Int {
        return when (position) {
            0 -> R.string.tv_tab_list_active
            1 -> R.string.tv_tab_list_archived
            else -> -1
        }
    }

    override fun getItemCount(): Int  = fragmentList.size

    override fun createFragment(position: Int): Fragment  = fragmentList[position]
}