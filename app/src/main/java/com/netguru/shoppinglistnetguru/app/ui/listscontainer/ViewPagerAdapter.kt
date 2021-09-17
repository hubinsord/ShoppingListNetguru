package com.netguru.shoppinglistnetguru.app.ui.listscontainer

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class ViewPagerAdapter(fragmentActivity: ListsContainerFragment, private val fragments: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int  = fragments.size

    override fun createFragment(position: Int): Fragment  = fragments[position]
}