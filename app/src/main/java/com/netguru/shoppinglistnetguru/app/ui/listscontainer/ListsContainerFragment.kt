package com.netguru.shoppinglistnetguru.app.ui.listscontainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.ui.archivedshoppinglists.ArchivedShoppingListsFragment
import com.netguru.shoppinglistnetguru.app.ui.shoppinglists.ShoppingListsFragment
import com.netguru.shoppinglistnetguru.databinding.FragmentListsContainerBinding


class ListsContainerFragment : Fragment() {

    private lateinit var binding: FragmentListsContainerBinding
    private lateinit var viewModel: ListsContainerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListsContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            ShoppingListsFragment.newInstance(),
            ArchivedShoppingListsFragment.newInstance()
        )
        binding.viewPager.adapter = ViewPagerAdapter(this, fragmentList)

//        val tabIcons = arrayListOf(R.drawable.format_list_black, R.drawable.archive_black)
//        val tabNames = arrayListOf(R.string.tl_tab_1, R.string.tl_tab_2)


        TabLayoutMediator(binding.tabLayout,
            binding.viewPager, TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
                when (position) {
                    0 -> { tab.text = "Shopping lists" }
                    1 -> { tab.text = "Archived lists" }
                }
            }).attach()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ListsContainerViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ListsContainerFragment()
    }
}
