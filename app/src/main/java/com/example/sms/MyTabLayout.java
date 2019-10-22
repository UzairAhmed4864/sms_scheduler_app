package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.sms.adapter.FragmentAdapter;
import com.example.sms.fragment.DefaultFragment;
import com.example.sms.fragment.GroupFragment;
import com.example.sms.fragment.HistoryFragment;
import com.example.sms.fragment.PlannedFragment;
import com.google.android.material.tabs.TabLayout;

public class MyTabLayout extends AppCompatActivity implements HistoryFragment.OnFragmentInteractionListener , PlannedFragment.OnFragmentInteractionListener , GroupFragment.OnFragmentInteractionListener , DefaultFragment.OnFragmentInteractionListener  {

    ImageView popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        TabLayout tabLayout = findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Planned"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Default"));
        //tabLayout.addTab(tabLayout.newTab().setText("Default"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //popup = findViewById(R.id.popup);

//        popup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Creating the instance of PopupMenu
//                PopupMenu popup = new PopupMenu(MyTabLayout.this, view);
//                //Inflating the Popup using xml file
//                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(
//                                MyTabLayout.this,
//                                "You Clicked : " + item.getTitle(),
//                                Toast.LENGTH_SHORT
//                        ).show();
//                        return true;
//                    }
//                });
//
//                popup.show(); //showing popup menu
//                 }
//        });

        final ViewPager viewPager = findViewById(R.id.pager);
        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
