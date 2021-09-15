package com.netguru.shoppinglistnetguru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.netguru.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.databinding.ActivityMainBinding
import com.netguru.shoppinglistnetguru.shoppinglistdetails.ShoppingListDetailsFragment
import com.netguru.shoppinglistnetguru.shoppinglists.ShoppingListsAdapter
import com.netguru.shoppinglistnetguru.shoppinglists.ShoppingListsFragment

class MainActivity : AppCompatActivity(), ShoppingListsAdapter.Companion.ShoppingListAdapterListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initViewModel()
        setInitialFragment()
    }

    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun setInitialFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(ShoppingListsFragment::class.java.name)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view,
                fragment ?: ShoppingListsFragment.newInstance(),
                ShoppingListsFragment::class.java.name)
            commit()
        }
    }

    override fun onShoppingListClicked(list: List<ShoppingItem>) {
        val fragment = supportFragmentManager.findFragmentByTag(ShoppingListDetailsFragment::class.java.name)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view,
                fragment ?: ShoppingListDetailsFragment.newInstance(list),
                ShoppingListDetailsFragment::class.java.name)
            commit()
            addToBackStack(null)
        }
    }
}