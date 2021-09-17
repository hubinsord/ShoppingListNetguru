package com.netguru.shoppinglistnetguru.app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.ui.listscontainer.ListsContainerFragment
import com.netguru.shoppinglistnetguru.databinding.ActivityMainBinding
import com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails.ShoppingListDetailsFragment
import com.netguru.shoppinglistnetguru.app.ui.shoppinglists.ShoppingListsAdapter
import com.netguru.shoppinglistnetguru.app.ui.shoppinglists.ShoppingListsFragment

class MainActivity : AppCompatActivity(),
    ShoppingListsAdapter.Companion.ShoppingListAdapterListener,
    ShoppingListDetailsFragment.Companion.ShoppingListDetailsFragmentListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
        setInitialFragment()
    }

    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun setInitialFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(ListsContainerFragment::class.java.name)
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left,
                R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right)
            replace(R.id.fragment_container_view,
                fragment ?: ListsContainerFragment.newInstance(),
                ListsContainerFragment::class.java.name)
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