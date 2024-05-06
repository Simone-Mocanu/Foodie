package com.example.foodie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.logging.Logger;


public class R_RecyclerViewAdapter extends RecyclerView.Adapter<R_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<RestaurantModel> restaurantModels;

    public R_RecyclerViewAdapter(Context context, ArrayList<RestaurantModel> restaurantModels) {
        this.context = context;

        this.restaurantModels = restaurantModels;

    }

    public void setRestaurantModels(ArrayList<RestaurantModel> restaurantModels) {
        this.restaurantModels = restaurantModels;
        notifyDataSetChanged();
    }
    //onCreateViewHolder -> for giving the layout 'restaurants_recycler_view_row' to each row
    @NonNull
    @Override
    public R_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RecyclerAdapter", "OnCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurants_recycler_view_row, parent, false);
        return new R_RecyclerViewAdapter.MyViewHolder(view);
    }

    //onBindViewHolder -> we give the data from 'restaurantModels' to each row
    @Override
    public void onBindViewHolder(@NonNull R_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Log.d("RecyclerAdapter", "OnBindViewHolder");
        //binding the data to the ViewHolder
        holder.restaurantName.setText(restaurantModels.get(position).getName());
        holder.restaurantDescription.setText(restaurantModels.get(position).getDescription());


        Picasso.get().load(restaurantModels.get(position).getImage()).fit().into(holder.restaurantImage);

        //holder.restaurantImage.setImageResource(restaurantModels.get(position).getImage());
    }

    //getItemCount -> for the adapter to know how many items we have in the list
    @Override
    public int getItemCount() {
        return restaurantModels.size();
    }

    //MyViewHolder -> looks like onCreate method, everytime we create a view holder
    //in 'onCreateViewHolder' we provide the card's components id to the viewholder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName, restaurantDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImageView);
            restaurantName = itemView.findViewById(R.id.restaurantNameTextView);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescriptionTextView);


        }
    }
}
