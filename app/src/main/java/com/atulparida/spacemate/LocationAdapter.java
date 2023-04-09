package com.atulparida.spacemate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ImageView locationImage, favouriteButton;
        Button bookingButton;
        TextView locationOccupancy;

        public VH(@NonNull View itemView) {
            super(itemView);
            locationImage = itemView.findViewById(R.id.location_image);
            favouriteButton = itemView.findViewById(R.id.location_favourite_button);
            bookingButton = itemView.findViewById(R.id.location_booking);
            locationOccupancy = itemView.findViewById(R.id.location_occupancy);
        }
    }
}
