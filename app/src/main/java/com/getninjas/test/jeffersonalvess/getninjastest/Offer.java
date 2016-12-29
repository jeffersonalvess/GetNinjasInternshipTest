package com.getninjas.test.jeffersonalvess.getninjastest;

import java.util.Date;

/**
 * Created by jeffersonalvess on 12/28/16.
 */

public class Offer {

    private String state;
    private Date createdAt;
    private String title;
    private String userName;
    private String city;
    private String neighborhood;
    private String uf;
    private String selfLink;

    public Offer() {
    }

    public Offer(String state, Date createdAt, String title, String userName, String city, String neighborhood, String uf, String selfLink) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
}
