package com.netguru.shoppinglistnetguru.shoppinglists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.data.model.ShoppingItem
import com.netguru.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.FragmentShoppingListsBinding
import java.lang.ClassCastException


class ShoppingListsFragment : Fragment(){
    private lateinit var binding: FragmentShoppingListsBinding
    private lateinit var listener: ShoppingListsAdapter.Companion.ShoppingListAdapterListener
    private lateinit var viewModel: ShoppingListsViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ShoppingListsAdapter.Companion.ShoppingListAdapterListener
        } catch (castException: ClassCastException){
            throw NotImplementedError("class cast fail")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShoppingListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initObservers()
        binding.fabAddNewList.setOnClickListener { fabAddNewListClicked() }
    }

    private fun initObservers() {
        viewModel.shoppingListsLiveData.observe(viewLifecycleOwner, { shoppingLists ->
            val adapter = ShoppingListsAdapter(shoppingLists, listener)
            binding.rvShoppingLists.adapter = adapter
            binding.rvShoppingLists.layoutManager = LinearLayoutManager(requireContext())
        })
    }

    private fun fabAddNewListClicked() {
        val testList = mutableListOf(ShoppingItem("dsadasd", 10))
        val shoppingList = ShoppingList("lista testowa", testList)
        viewModel.addNewShoppingList(shoppingList)
    }

    private fun initViewModel() {
        val viewModelFactory = ShoppingListsViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(ShoppingListsViewModel::class.java)
    }

    companion object {
        fun newInstance() = ShoppingListsFragment()
    }
}