package com.getninjas.test.jeffersonalvess.getninjastest;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONObject;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String offersURI, leadsURI, rOffersURI, rLeadsURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  Getting the first information from the REST API
        String baseURI = "https://testemobile.getninjas.com.br";

        try
        {
            JSONObject json = new RetrieveJSONTask().execute(baseURI).get();
            offersURI = json.getJSONObject("_links").getJSONObject("offers").optString("href");
            leadsURI = json.getJSONObject("_links").getJSONObject("leads").optString("href");

            //  There is a problem with the data received from the request.
            //  The correct URL is under https, but the JSON data is typed as HTTP
            //  Due a time constraint this change will be made by hardconding the feature

            offersURI = offersURI.replace("http", "https");
            leadsURI = leadsURI.replace("http", "https");

            json = new RetrieveJSONTask().execute(offersURI).get();
            rOffersURI = json.getJSONObject("_links").getJSONObject("self").optString("href");

            json = new RetrieveJSONTask().execute(leadsURI).get();
            rLeadsURI = json.getJSONObject("_links").getJSONObject("self").optString("href");
        }
        catch (Exception e)
        {}

        //  Setup tabbed layout
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
        Bundle offerBundle = new Bundle();
        Bundle leadBundle = new Bundle();

        offerBundle.putString("URI", offersURI);
        offerBundle.putString("rURI", rOffersURI);

        leadBundle.putString("URI", leadsURI);
        leadBundle.putString("rURI", rLeadsURI);

        OffersFragment offersFragment = new OffersFragment();
        offersFragment.setArguments(offerBundle);

        LeadsFragment leadsFragment = new LeadsFragment();
        leadsFragment.setArguments(leadBundle);

        adapter.addFragment(offersFragment, "AVAILABLE");
        adapter.addFragment(leadsFragment, "ACCEPTED");
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





