package com.netguru.shoppinglist.app.ui.shoplistscontainer.container;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.netguru.shoppinglist.app.ui.shoplistscontainer.active.ActiveShopListsFragment;
import com.netguru.shoppinglist.app.ui.shoplistscontainer.archived.ArchivedShopListsFragment;

import java.util.NoSuchElementException;

public class ShopListsAdapter extends FragmentStateAdapter {

    public ShopListsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public Integer getItemNameRes(Integer position) {
        return ShopListsNameResolver.INSTANCE.getItemNameRes(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return ActiveShopListsFragment.Companion.newInstance();
            case 1:
                return ArchivedShopListsFragment.Companion.newInstance();
            default:
                throw new NoSuchElementException();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}