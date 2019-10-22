package com.example.sms.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sms.fragment.DefaultFragment;
import com.example.sms.fragment.GroupFragment;
import com.example.sms.fragment.HistoryFragment;
import com.example.sms.fragment.PlannedFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    int mNoOfTabs;

    public FragmentAdapter(FragmentManager fm , int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {

            case 0:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;

            case 1:
                PlannedFragment plannedFragment = new PlannedFragment();
                return plannedFragment;


            case 2:
                GroupFragment tab3 = new GroupFragment();
                return tab3;

            case 3:
                DefaultFragment tab4 = new DefaultFragment();
                return tab4;



            default:
                    return null;
        }


    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
