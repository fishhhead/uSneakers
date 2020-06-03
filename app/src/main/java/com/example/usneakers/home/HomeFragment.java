package com.example.usneakers.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.usneakers.R;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}

