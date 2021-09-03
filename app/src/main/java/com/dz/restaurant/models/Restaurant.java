package com.dz.restaurant.models;

public class Restaurant {
    private String restaurant_img;
    private String RestaurantName;
    private String RestaurantPhone;
    private String RestaurantWebsite;
    private String Hours;
    private String City;
    private String State;
    private String PostalCode;
    private String Streat;
    private String Formatted;

    public String getRestaurant_img() {
        return restaurant_img;
    }

    public void setRestaurant_img(String restaurant_img) {
        this.restaurant_img = restaurant_img;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getRestaurantPhone() {
        return RestaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        RestaurantPhone = restaurantPhone;
    }

    public String getRestaurantWebsite() {
        return RestaurantWebsite;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        RestaurantWebsite = restaurantWebsite;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getStreat() {
        return Streat;
    }

    public void setStreat(String streat) {
        Streat = streat;
    }

    public String getFormatted() {
        return Formatted;
    }

    public void setFormatted(String formatted) {
        Formatted = formatted;
    }
}
