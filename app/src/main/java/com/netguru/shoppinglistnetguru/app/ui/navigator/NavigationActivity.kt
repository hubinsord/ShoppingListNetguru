package com.netguru.shoppinglistnetguru.app.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.ShopListsContainerFragment
import com.netguru.shoppinglistnetguru.databinding.ActivityMainBinding
import com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails.ShoppingListDetailsFragment
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active.ActiveShopListsAdapter

class NavigationActivity : AppCompatActivity(),
    ActiveShopListsAdapter.Companion.ShoppingListAdapterListener,
    ShoppingListDetailsFragment.Companion.ShoppingListDetailsFragmentListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setInitialFragment()
    }

    private fun setInitialFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(ShopListsContainerFragment::class.java.name)
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left,
                R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right)
            replace(R.id.fragment_container_view,
                fragment ?: ShopListsContainerFragment.newInstance(),
                ShopListsContainerFragment::class.java.name)
            commit()
        }
    }

    override fun onShoppingListClicked(shoppingList: ShoppingList) {
        val fragment = supportFragmentManager.findFragmentByTag(ShoppingListDetailsFragment::class.java.name)
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left,
                R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right)
            replace(R.id.fragment_container_view,
                fragment ?: ShoppingListDetailsFragment.newInstance(shoppingList),
                ShoppingListDetailsFragment::class.java.name)
            commit()
            addToBackStack(null)
        }
    }

    override fun onBtnArchiveListClicked() {
        supportFragmentManager.popBackStack()
    }
}