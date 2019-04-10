package com.jambit.maluku.android.maluku.android.malukuandroidapp;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballPlayerList;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballTable;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter.MyPagerAdapter;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.AppCenter; import com.microsoft.appcenter.analytics.Analytics; import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity implements FoosballPlayerList.OnFragmentInteractionListener, FoosballTable.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        AppCenter.start(getApplication(), "de06fa9c-5f5f-464d-8322-aa8406194d94", Analytics.class, Crashes.class);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
