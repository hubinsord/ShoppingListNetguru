package com.netguru.shoppinglistnetguru.shoppinglistdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.data.model.ShoppingItem
import com.netguru.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.FragmentShoppingListDetailsBinding

class ShoppingListDetailsFragment(
    private var shoppingList: ShoppingList
) : Fragment(){

    private lateinit var binding: FragmentShoppingListDetailsBinding
    private lateinit var viewModel: ShoppingListDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShoppingListDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel.updateShoppingList(shoppingList)
        viewModel.shoppingListLiveData.observe(viewLifecycleOwner, {shoppingList ->
            binding.rvItems.adapter = ShoppingListDetailsAdapter(shoppingList)
            binding.rvItems.layoutManager = LinearLayoutManager(requireContext())
        })
        binding.fabAddNewItem.setOnClickListener { fabAddNewItemClicked()  }
    }

    private fun fabAddNewItemClicked() {
        val shoppingItem = ShoppingItem("dodane", 20)
        viewModel.addNewItem(shoppingItem)
    }

    private fun initViewModel() {
        val viewModelFactory = ShoppingListDetailViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(ShoppingListDetailsViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(shoppingList: ShoppingList) = ShoppingListDetailsFragment(shoppingList)
    }
}
