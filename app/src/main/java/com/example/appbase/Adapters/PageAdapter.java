package com.example.appbase.Adapters;


import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by YosvaCr7 on 6/11/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {

    // private ArrayList<android.support.v4.app.Fragment> fragments;
    private final ArrayList<Fragment> listFragment = new ArrayList<>();
    private final ArrayList<String> listFragmentTitle = new ArrayList<>();

    public PageAdapter(FragmentManager fm) {
        super(fm);

        //  this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);

    }

    @Override
    public int getCount() {
        return listFragment.size();
    }


    public void addFragment(Fragment fragment, String title) {

        listFragment.add(fragment);
        listFragmentTitle.add(title);


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmentTitle.get(position);
    }
}

