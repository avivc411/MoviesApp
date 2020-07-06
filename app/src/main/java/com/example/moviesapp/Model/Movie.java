package com.example.moviesapp.Model;

import java.util.List;

public class Movie {
    private String name, category, description, imgURL, promoURL, hour;
    private int id, year;
    private double rate;
    private List<String> imgURLList;

    public Movie(String name, String category, int id, int year) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.year = year;
    }

    public Movie(String name, String category, String description, String imgURL, String promoURL, String hour, int id, int year, double rate, List<String> imgURLList) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imgURL = imgURL;
        this.promoURL = promoURL;
        this.hour = hour;
        this.id = id;
        this.year = year;
        this.rate = rate;
        this.imgURLList = imgURLList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getPromoURL() {
        return promoURL;
    }

    public String getHour() {
        return hour;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public double getRate() {
        return rate;
    }

    public List<String> getImgURLList() {
        return imgURLList;
    }
}
