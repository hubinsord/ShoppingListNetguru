package com.netguru.shoppinglist.app.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.netguru.shoppinglist.app.data.model.ShoppingList
import com.netguru.shoppinglist.R
import com.netguru.shoppinglist.app.ui.shoplistscontainer.container.ShopListsContainerFragment
import com.netguru.shoppinglist.databinding.ActivityMainBinding
import com.netguru.shoppinglist.app.ui.shoppinglistdetails.ShopListDetailsFragment
import com.netguru.shoppinglist.app.ui.shoplistscontainer.active.ActiveShopListsAdapter
import com.netguru.shoppinglist.app.ui.shoplistscontainer.container.ShopListsContainerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavigationActivity : AppCompatActivity(),
    ActiveShopListsAdapter.Companion.ShoppingListAdapterListener,
    ShopListDetailsFragment.Companion.ShoppingListDetailsFragmentListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onShoppingListClicked(shoppingList: ShoppingList) {
        val action = ShopListsContainerFragmentDirections.actionShopListsContainerFragmentToShopListDetailsFragment(shoppingList)
        findNavController(R.id.fragment_container_view).navigate(action)
    }

    override fun onListArchived() {
        supportFragmentManager.popBackStack()
    }
}