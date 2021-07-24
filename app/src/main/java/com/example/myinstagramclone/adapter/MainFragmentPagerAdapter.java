package com.example.myinstagramclone.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myinstagramclone.views.HomeFragment;
import com.example.myinstagramclone.views.LikesFragment;
import com.example.myinstagramclone.views.ProfileFragment;
import com.example.myinstagramclone.views.SearchFragment;

public class MainFragmentPagerAdapter extends FragmentStateAdapter {

    public MainFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new LikesFragment();
            case 3:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
