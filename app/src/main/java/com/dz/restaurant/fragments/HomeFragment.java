package com.dz.restaurant.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dz.restaurant.R;
import com.dz.restaurant.activities.MainActivity;
import com.dz.restaurant.adapters.RestaurantAdapter;
import com.dz.restaurant.helpers.ModelHelper;
import com.dz.restaurant.helpers.SqlHelper;
import com.dz.restaurant.interfaces.SqlDelegate;
import com.dz.restaurant.models.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SqlDelegate {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private View view;

    // Widgets
    private EditText serach_restaurant;
    private RecyclerView rv_restaurant_list;
    private TextView restaurant_view_type;

    private ArrayList<Restaurant> restaurants;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();

        // Widget Initialization
        rv_restaurant_list = view.findViewById(R.id.rv_restaurant_list);
        serach_restaurant = view.findViewById(R.id.serach_restaurant);
        restaurant_view_type = view.findViewById(R.id.restaurant_view_type);

        serach_restaurant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GetRestaurantWithKeyWord(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
        GetNearbyRestaurant();
        return view;
    }

    private void GetRestaurantWithKeyWord(String restaurant_name) {
        MainActivity.progress_bar.setVisibility(View.VISIBLE);
        SqlHelper sqlHelper = new SqlHelper(context, HomeFragment.this);
        sqlHelper.setExecutePath("restaurants/search/fields");
        sqlHelper.setMethod("GET");
        sqlHelper.setActionString("RestaurantList");
        ContentValues params = new ContentValues();
        params.put("key", MainActivity.API_Key);
        params.put("restaurant_name", restaurant_name);
        sqlHelper.setParams(params);
        sqlHelper.executeUrl(false);
    }
    private void GetNearbyRestaurant() {
        MainActivity.progress_bar.setVisibility(View.VISIBLE);
        SqlHelper sqlHelper = new SqlHelper(context, HomeFragment.this);
        sqlHelper.setExecutePath("restaurants/search/geo");
        sqlHelper.setMethod("GET");
        sqlHelper.setActionString("RestaurantList");
        ContentValues params = new ContentValues();
        params.put("key", MainActivity.API_Key);
        params.put("lat", MainActivity.Latitude);
        params.put("lon", MainActivity.Longitude);
        params.put("distance", 10);
        params.put("size", 25);
        params.put("page", 1);
        sqlHelper.setParams(params);
        sqlHelper.executeUrl(false);
    }
    private void GetAllRestaurant() {
        MainActivity.progress_bar.setVisibility(View.VISIBLE);
        SqlHelper sqlHelper = new SqlHelper(context, HomeFragment.this);
        sqlHelper.setExecutePath("restaurants/search/geo");
        sqlHelper.setMethod("GET");
        sqlHelper.setActionString("RestaurantList");
        ContentValues params = new ContentValues();
        params.put("key", MainActivity.API_Key);
        params.put("lat", MainActivity.Latitude);
        params.put("lon", MainActivity.Longitude);
        params.put("distance", 50000);
        params.put("size", 25);
        params.put("page", 1);
        sqlHelper.setParams(params);
        sqlHelper.executeUrl(false);
    }

    // BUILDING & CREATING POST FOR POSTS
    private void buildRestaurant(JSONArray jsonArray) {
        try{
            restaurants = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                Restaurant restaurant = new ModelHelper(context).buildRestaurantModel(jsonArray.getJSONObject(i));
                restaurants.add(restaurant);
            }
            createRestaurantList();
        }catch (Exception e){ }
    }

    private void createRestaurantList() {
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(context, restaurants, view,rv_restaurant_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rv_restaurant_list.setLayoutManager(layoutManager);
        rv_restaurant_list.setAdapter(restaurantAdapter);
    }

    @Override
    public void onResponse(SqlHelper sqlHelper) {
        String Response = sqlHelper.getStringResponse();
        MainActivity.progress_bar.setVisibility(View.GONE);
        try{
            switch (sqlHelper.getActionString()){
                case "RestaurantList":{
                    JSONObject jsonObject = new JSONObject(Response);
                    if (jsonObject.getJSONArray("data").length() == 0){
                        restaurant_view_type.setText("Getting All Restaurants");
                        Toast.makeText(context, "No Nearby Restaurants", Toast.LENGTH_SHORT).show();
                        GetAllRestaurant();
                    }else {
                        restaurant_view_type.setVisibility(View.GONE);
                        buildRestaurant(jsonObject.getJSONArray("data"));
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}