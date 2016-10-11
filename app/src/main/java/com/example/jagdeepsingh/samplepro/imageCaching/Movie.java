package com.example.jagdeepsingh.samplepro.imageCaching;

/**
 * Created by jagdeep.singh on 05-10-2016.
 */

public class Movie {
    private String imageUrl, title, genre, year;

    public Movie() {
    }

    public Movie(String imageUrl, String title, String genre, String year) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
