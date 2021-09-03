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


    public Restaurant buildRestaurantModel() {
        try {
            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurant_img("https://www.gannett-cdn.com/presto/2020/02/17/USAT/80fa5afd-dd88-407d-aad1-9e25f8ad3b47-04_HOUSTON_Coronavirus_restaurants_021.JPG");
            restaurant.setRestaurantName("Desy's Clam Bar");
            restaurant.setRestaurantPhone("(347) 599-0267");
            restaurant.setHours("Daily: 11am-12:30am");
            restaurant.setRestaurantWebsite("");

            return restaurant;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Restaurant();
    }

}