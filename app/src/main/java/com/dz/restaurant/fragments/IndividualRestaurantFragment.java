package com.dz.restaurant.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dz.restaurant.R;
import com.dz.restaurant.adapters.RestaurantAdapter;
import com.dz.restaurant.helpers.GlideHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndividualRestaurantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndividualRestaurantFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private View rootView;

    // Widgets
    private ImageView restaurant_img;
    private TextView restaurant_name,restaurant_hours,restaurant_contact;

    public IndividualRestaurantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndividualRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndividualRestaurantFragment newInstance(String param1, String param2) {
        IndividualRestaurantFragment fragment = new IndividualRestaurantFragment();
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
        rootView = inflater.inflate(R.layout.fragment_individual_restaurant, container, false);
        context = getContext();

        restaurant_img = rootView.findViewById(R.id.restaurant_img);
        restaurant_name = rootView.findViewById(R.id.restaurant_name);
        restaurant_hours = rootView.findViewById(R.id.restaurant_hours);
        restaurant_contact = rootView.findViewById(R.id.restaurant_contact);

        GlideHelper.setImageUsingGlide(context, RestaurantAdapter.restaurants.get(Integer.parseInt(mParam1)).getRestaurant_img(),restaurant_img);
        restaurant_name.setText(RestaurantAdapter.restaurants.get(Integer.parseInt(mParam1)).getRestaurantName());
        restaurant_hours.setText(RestaurantAdapter.restaurants.get(Integer.parseInt(mParam1)).getHours());
        restaurant_contact.setText(RestaurantAdapter.restaurants.get(Integer.parseInt(mParam1)).getRestaurantPhone());


        return rootView;
    }
}