package com.example.usneakers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View view;
    Adapter adapter;


    private void setupViewPager(ViewPager viewPager){
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new HomeIntroFragment(), "Intro");
        adapter.addFragment(new HomeGallaryFragment(), "Gallery");
        viewPager.setAdapter(adapter);
    }

    /*public void AddSubFragment() {
        getChildFragmentManager().beginTransaction().add(R.id.HomeContainer, new HomeGallaryFragment()).commit();
        getChildFragmentManager().executePendingTransactions();
    }*/
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

       /* ViewPager viewPager = getView().findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tablayout = getView().findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);*/
        //AddSubFragment();
        return view;
    }

}
