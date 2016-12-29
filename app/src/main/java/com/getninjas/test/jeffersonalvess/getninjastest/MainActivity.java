package com.getninjas.test.jeffersonalvess.getninjastest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Setup icons to the tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_offers);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_leads);

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OffersFragment(), "DISPONÍVEIS");
        adapter.addFragment(new LeadsFragment(), "ACEITOS");
        viewPager.setAdapter(adapter);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

//        A code for snackbar, it will be useful to implement internet connection check.
//        if (leads.size() < 1) {
//            final Snackbar snackBarLeads = Snackbar.make(rvLeads, "Ooops, it was not possible to retrieve your leads at this moment.", Snackbar.LENGTH_INDEFINITE);
//
//            snackBarLeads.setAction("Ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    snackBarLeads.dismiss();
//                }
//            });
//            snackBarLeads.show();
//        }




