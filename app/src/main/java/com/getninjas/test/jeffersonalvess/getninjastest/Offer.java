package com.getninjas.test.jeffersonalvess.getninjastest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by jeffersonalvess on 12/28/16.
 */

public class Offer {

    private String state;
    private String createdAt;
    private String title;
    private String userName;
    private String city;
    private String neighborhood;
    private String uf;
    private String selfLink;

    public Offer() {
    }

    public Offer(String state, String createdAt, String title, String userName, String city, String neighborhood, String uf, String selfLink) {
        this.state = state;
        this.createdAt = createdAt;
        this.title = title;
        this.userName = userName;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.selfLink = selfLink;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }


    //  This section populates a ArrayList with Offers
    public static ArrayList<Offer> createOffersList(String uri) throws IOException, JSONException, ExecutionException, InterruptedException {
        ArrayList<Offer> offers = new ArrayList<Offer>();

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject json = new RetrieveJSONTask().execute(uri).get();
            JSONArray jsonArray = new JSONArray(json.optString("offers"));

            Log.i(MainActivity.class.getName(),
                    "Number of entries " + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jO = jsonArray.getJSONObject(i);

                Offer offer = new Offer();

                offer.setState(jO.optString("state"));

                offer.setCreatedAt(jO.getJSONObject("_embedded").getJSONObject("request").optString("created_at"));
                offer.setTitle(jO.getJSONObject("_embedded").getJSONObject("request").optString("title"));

                offer.setUserName(jO.getJSONObject("_embedded").getJSONObject("request").getJSONObject("_embedded").getJSONObject("user").optString("name"));

                offer.setCity(jO.getJSONObject("_embedded").getJSONObject("request").getJSONObject("_embedded").getJSONObject("address").optString("city"));
                offer.setNeighborhood(jO.getJSONObject("_embedded").getJSONObject("request").getJSONObject("_embedded").getJSONObject("address").optString("neighborhood"));
                offer.setUf(jO.getJSONObject("_embedded").getJSONObject("request").getJSONObject("_embedded").getJSONObject("address").optString("uf"));


                offer.setSelfLink(jO.getJSONObject("_links").getJSONObject("self").optString("href"));


                offers.add(offer);

            }
        }
        finally {

        }

        return offers;
    }
}
