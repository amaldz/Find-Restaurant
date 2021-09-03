package com.dz.restaurant.helpers;


import android.content.Context;

import com.dz.restaurant.models.Restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by amaldas on 05/09/20.
 */

public class ModelHelper {

    Context context;
    public ModelHelper(Context context) {
        this.context = context;
    }


    public Restaurant buildRestaurantModel(JSONObject jsonObject) {
        try {
            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurant_img("https://www.foodbusinessnews.net/ext/resources/2020/4/CoupleAtRestaurant_Lead.jpg");

            if (jsonObject.getString("restaurant_name").equals("")){
                restaurant.setRestaurantName("Restaurant name Not Available");
            }else {
                restaurant.setRestaurantName(jsonObject.getString("restaurant_name"));
            }

            if (jsonObject.getString("restaurant_phone").equals("")){
                restaurant.setRestaurantPhone("Phone Number Not Available");
            }else {
                restaurant.setRestaurantPhone(jsonObject.getString("restaurant_phone"));
            }

            if (jsonObject.getString("hours").equals("")){
                restaurant.setHours("Working Hours Not Available");
            }else {
                restaurant.setHours(jsonObject.getString("hours"));
            }

            if (jsonObject.getString("restaurant_website").equals("")){
                restaurant.setRestaurantWebsite("Restaurant website Not Available");
            }else {
                restaurant.setRestaurantWebsite(jsonObject.getString("restaurant_website"));
            }

            if (jsonObject.getJSONObject("address").getString("city").equals("")){
                restaurant.setCity(jsonObject.getString("restaurant_website"));
            }else {
                restaurant.setCity("Address Not Available");
            }

            if (jsonObject.getJSONObject("address").getString("state").equals("")){
                restaurant.setState(jsonObject.getString("state"));
            }else {
                restaurant.setState("");
            }

            if (jsonObject.getJSONObject("address").getString("postal_code").equals("")){
                restaurant.setPostalCode(jsonObject.getString("postal_code"));
            }else {
                restaurant.setPostalCode("");
            }

            if (jsonObject.getJSONObject("address").getString("street").equals("")){
                restaurant.setStreat(jsonObject.getString("street"));
            }else {
                restaurant.setStreat("");
            }

            if (jsonObject.getJSONObject("address").getString("formatted").equals("")){
                restaurant.setFormatted(jsonObject.getString("street"));
            }else {
                restaurant.setFormatted("");
            }

            return restaurant;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Restaurant();
    }

}