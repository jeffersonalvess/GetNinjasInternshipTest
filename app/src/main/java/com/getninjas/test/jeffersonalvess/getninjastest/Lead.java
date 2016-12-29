package com.getninjas.test.jeffersonalvess.getninjastest;

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

public class Lead {

    private String createdAt;
    private String city;
    private String street;
    private String neighborhood;
    private String uf;
    private String name;
    private String email;
    private String title;
    private String selfLink;

    public Lead() {
    }

    public Lead(String createdAt, String city, String street, String neighborhood, String uf, String name, String email, String title, String selfLink) {
        this.createdAt = createdAt;
        this.city = city;
        this.street = street;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.name = name;
        this.email = email;
        this.title = title;
        this.selfLink = selfLink;
    }

    public String getCreatedAt() { return createdAt;}

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }


    //  This section populates a ArrayList with Leads
    public static ArrayList<Lead> createOffersList(String uri) throws IOException, JSONException, ExecutionException, InterruptedException {
        ArrayList<Lead> leads = new ArrayList<Lead>();

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject json = new RetrieveJSONTask().execute(uri).get();
            JSONArray jsonArray = new JSONArray(json.optString("leads"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jO = jsonArray.getJSONObject(i);

                Lead lead = new Lead();

                lead.setCreatedAt(jO.optString("created_at"));

                lead.setCity(jO.getJSONObject("_embedded").getJSONObject("address").optString("city"));
                lead.setStreet(jO.getJSONObject("_embedded").getJSONObject("address").optString("street"));
                lead.setNeighborhood(jO.getJSONObject("_embedded").getJSONObject("address").optString("neighborhood"));
                lead.setUf(jO.getJSONObject("_embedded").getJSONObject("address").optString("uf"));

                lead.setName(jO.getJSONObject("_embedded").getJSONObject("user").optString("name"));
                lead.setEmail(jO.getJSONObject("_embedded").getJSONObject("user").optString("email"));

                lead.setTitle(jO.getJSONObject("_embedded").getJSONObject("request").optString("title"));

                lead.setSelfLink(jO.getJSONObject("_links").getJSONObject("self").optString("href"));

                leads.add(lead);

            }
        }
        catch (Exception e)
        {
            return leads;
        }

        return leads;
    }
}
