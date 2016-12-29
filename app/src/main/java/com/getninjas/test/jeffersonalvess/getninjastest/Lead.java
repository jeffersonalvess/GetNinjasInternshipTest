package com.getninjas.test.jeffersonalvess.getninjastest;

import java.util.Date;

/**
 * Created by jeffersonalvess on 12/28/16.
 */

public class Lead {

    private Date createdAt;
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

    public Lead(Date createdAt, String city, String street, String neighborhood, String uf, String name, String email, String title, String selfLink) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
}
