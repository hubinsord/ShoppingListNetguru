package com.netguru.shoppinglistnetguru.app.ui.archivedshoppinglists

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.shoppinglistnetguru.app.ui.shoppinglists.ShoppingListsAdapter
import com.netguru.shoppinglistnetguru.databinding.FragmentArchivedShoppingListsBinding


class ArchivedShoppingListsFragment : Fragment() {

    private lateinit var binding: FragmentArchivedShoppingListsBinding
    private lateinit var viewModel: ArchivedShoppingListsViewModel
    private lateinit var adapter: ArchivedShoppingListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVieModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentArchivedShoppingListsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllShoppingLists(1)
        initObservers()
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
    viewModel.shoppingListsLiveData.observe(viewLifecycleOwner, {archivedShoppingLists ->
        if (archivedShoppingLists.isNotEmpty()) {
            Log.i("TEST", "TEST init obs")
            adapter.archivedShoppingLists = archivedShoppingLists
            adapter.notifyDataSetChanged()
        }
    })
    }

    private fun initVieModel() {
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(requireActivity().application).
            create(ArchivedShoppingListsViewModel::class.java)
    }

    private fun initViews() {
        adapter = ArchivedShoppingListsAdapter(mutableListOf())
        binding.rvShoppingLists.adapter = adapter
        binding.rvShoppingLists.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShoppingLists.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }


    companion object {
        fun newInstance() = ArchivedShoppingListsFragment()
    }
}