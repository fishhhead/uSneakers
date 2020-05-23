package com.example.usneakers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    String title;

    private Fragment[] HomechildFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new HomeIntroFragment();
            case 1:
                return new HomeGallaryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        /*switch (position){
            case 0:
                title = getItem(position).getString(R.string.Intro);
                break;
            case 1:
                title = getItem(position).getString(R.string.Gallery);
        }*/
        String title = getItem(position).getClass().getName();

        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
