package com.dz.restaurant.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dz.restaurant.R;
import com.dz.restaurant.activities.MainActivity;
import com.dz.restaurant.fragments.IndividualRestaurantFragment;
import com.dz.restaurant.helpers.GlideHelper;
import com.dz.restaurant.models.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    private Context context;
    private View rootview;
    private RecyclerView rv_semesters;
    public static ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants
            , View view, RecyclerView rv_semesters) {
        this.context = context;

        this.rootview = view;
        this.rv_semesters = rv_semesters;
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.restaurant_listing_layout,parent,false);

        return new RestaurantAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if (holder instanceof RestaurantAdapter.ViewHolder) {
                holder.restaurant_name.setText(restaurants.get(position).getRestaurantName());
                GlideHelper.setImageUsingGlide(context,restaurants.get(position).getRestaurant_img(), holder.restaurant_img);

                holder.ll_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IndividualRestaurantFragment individualRestaurantFragment = IndividualRestaurantFragment.newInstance(String.valueOf(position), "");
                        ((MainActivity)context).initFragment(individualRestaurantFragment);
                    }
                });
            }
        }catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView restaurant_name;
        private ImageView restaurant_img;
        private LinearLayout ll_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurant_img = itemView.findViewById(R.id.restaurant_img);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            ll_item = itemView.findViewById(R.id.ll_item);
        }
    }
}
