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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class OffersFragment extends Fragment {

    ArrayList<Offer> offers;
    OffersAdapter adapter;
    private SwipeRefreshLayout swipeContainer;


    public OffersFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvOffers = (RecyclerView) getView().findViewById(R.id.recycler_view);

        try {
            offers = Offer.createOffersList("https://testemobile.getninjas.com.br/offers");
            adapter = new OffersAdapter(getActivity(), offers);
            rvOffers.setAdapter(adapter);
            rvOffers.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //  Pull to refresh
        swipeContainer = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefreshLayoutOffer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    fetchTimelineAsync(0, "https://testemobile.getninjas.com.br/offers");
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
        offers = Offer.createOffersList(link);
        adapter.addAll(offers);

        //  Set refresh as done
        swipeContainer.setRefreshing(false);

    }
}
