package com.getninjas.test.jeffersonalvess.getninjastest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class LeadsFragment extends Fragment {

    ArrayList<Lead> leads;
    LeadsAdapter adapter;
    private SwipeRefreshLayout swipeContainer;

    public LeadsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leads, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvLeads = (RecyclerView) getView().findViewById(R.id.recycler_view);

        try {
            leads = Lead.createOffersList("https://testemobile.getninjas.com.br/leads");
            adapter = new LeadsAdapter(getActivity(), leads);
            rvLeads.setAdapter(adapter);
            rvLeads.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //  Pull to refresh
        swipeContainer = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayoutLead);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    fetchTimelineAsync(0, "https://testemobile.getninjas.com.br/leads");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    public void fetchTimelineAsync(int page, String link) throws InterruptedException, ExecutionException, JSONException, IOException {

        //  Clear the adapter
        adapter.clear();

        //  Get the new DataArray
        leads = Lead.createOffersList(link);
        adapter.addAll(leads);

        //  Set refresh as done
        swipeContainer.setRefreshing(false);

    }
}
