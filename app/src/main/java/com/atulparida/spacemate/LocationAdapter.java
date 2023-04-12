package com.atulparida.spacemate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.VH>{

    List<Location> locationList;

    public LocationAdapter(List<Location> locationList) {
        this.locationList = locationList;

    }

    @NonNull
    @Override
    public LocationAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View locationView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card_template, parent, false);
        VH vh = new VH(locationView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.VH holder, int position) {
        Location location = locationList.get(position);
        holder.locationOccupancy.setText(location.getParsedCapacity());
        holder.locationImage.setImageResource(location.getImgResource());
        holder.bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("location", location);
                Intent intent = new Intent(holder.context, CommonLocationActivity.class);
                intent.putExtras(bundle);
                //Pass current location through to activity if clicked
                holder.context.startActivity(intent);

            }
        });

        holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location.setFavourite(!location.isFavourite());
                //TODO: Add logic for adding or removing from favourites list
                if (location.isFavourite()) {
                    Toast.makeText(holder.context, location.getName() + "is a favourite :)", Toast.LENGTH_SHORT).show();
                    holder.favouriteButton.setImageResource(R.drawable.baseline_favorite_24);
                    //Case 1 holder is in favourites, let it be


                    //Case 2 holder is not in favourites, add to favourites

                }
                else {
                    Toast.makeText(holder.context, location.getName() + "is not a favourite :(", Toast.LENGTH_SHORT).show();
                    holder.favouriteButton.setImageResource(R.drawable.baseline_favorite_border_24);
                    //Case 1 holder is in favourites, remove holder
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ImageView locationImage, favouriteButton;
        Button bookingButton;
        TextView locationOccupancy;

        Context context;

        public VH(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            locationImage = itemView.findViewById(R.id.location_image);
            favouriteButton = itemView.findViewById(R.id.location_favourite_button);
            bookingButton = itemView.findViewById(R.id.location_booking);
            locationOccupancy = itemView.findViewById(R.id.location_occupancy);
        }
    }
}
