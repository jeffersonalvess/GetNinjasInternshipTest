package com.getninjas.test.jeffersonalvess.getninjastest;

import java.util.ArrayList;

/**
 * Created by jeffersonalvess on 12/30/16.
 */

public class Detail {
    private int distance;
    private int leadPrice;
    private String title;
    private ArrayList<Info> info;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String neighborhood;
    private String uf;
    private float geoLatitude;
    private float geoLongitude;
    private String linkAccept;
    private String linkReject;

    public Detail() {}

    public Detail(int distance, int leadPrice, String title, ArrayList<Info> info, String name, String email, String phone, String city, String neighborhood, String uf, float geoLatitude, float geoLongitude, String linkAccept, String linkReject) {
        this.distance = distance;
        this.leadPrice = leadPrice;
        this.title = title;
        this.info = info;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
        this.linkAccept = linkAccept;
        this.linkReject = linkReject;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLeadPrice() {
        return leadPrice;
    }

    public void setLeadPrice(int leadPrice) {
        this.leadPrice = leadPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Info> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phones) {
        this.phone = phones;
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

    public float getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(float geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public float getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(float geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public String getLinkAccept() {
        return linkAccept;
    }

    public void setLinkAccept(String linkAccept) {
        this.linkAccept = linkAccept;
    }

    public String getLinkReject() {
        return linkReject;
    }

    public void setLinkReject(String linkReject) {
        this.linkReject = linkReject;
    }

}

