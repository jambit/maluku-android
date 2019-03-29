package com.jambit.maluku.android.maluku.android.malukuandroidapp;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballChat;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballTable;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements FoosballChat.OnFragmentInteractionListener, FoosballTable.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
