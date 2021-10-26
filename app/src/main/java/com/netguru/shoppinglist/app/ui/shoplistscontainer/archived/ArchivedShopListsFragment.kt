package com.netguru.shoppinglist.app.ui.shoplistscontainer.archived

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.shoppinglist.databinding.FragmentArchivedShoppingListsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchivedShopListsFragment : Fragment() {

    private lateinit var binding: FragmentArchivedShoppingListsBinding
    private lateinit var adapter: ArchivedShopListsAdapter
    private val viewModel: ArchivedShopListsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentArchivedShoppingListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllShoppingLists(true)
        initObservers()
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.shoppingListsLiveData.observe(viewLifecycleOwner, { archivedShoppingLists ->
            if (archivedShoppingLists.isNotEmpty()) {
                adapter.archivedShoppingLists = archivedShoppingLists
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initViews() {
        adapter = ArchivedShopListsAdapter(mutableListOf())
        binding.rvShoppingLists.adapter = adapter
        binding.rvShoppingLists.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShoppingLists.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    companion object {
        fun newInstance() = ArchivedShopListsFragment()
    }
}