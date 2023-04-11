package com.atulparida.spacemate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.VH>{

    List<Location> itemList;

    public FavouriteAdapter(List<Location> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View favoriteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card_template, parent, false);
        VH vh = new VH(favoriteView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.VH holder, int position) {
        Location location = itemList.get(position);
        holder.locationOccupancy.setText(location.getParsedCapacity());
        holder.locationImage.setImageURI(location.getUri());
        holder.bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context, CommonLocationActivity.class);
                intent.putExtra("serialLocation", location);
                //Pass current location through to activity if clicked
                holder.context.startActivity(intent);

            }
        });

        holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location.setFavourite(!location.isFavourite());
                //TODO: Add logic for adding or removing from favourites list
                if (!location.isFavourite()) {
                    Toast.makeText(holder.context, location.getName() + "is not a favourite :(", Toast.LENGTH_SHORT).show();
                    holder.favouriteButton.setImageResource(R.drawable.baseline_favorite_border_24);
                    //Case 1 holder is in favourites, remove holder
                    int currPosition = holder.getAdapterPosition();
                    itemList.remove(currPosition);
                    notifyItemRemoved(currPosition);
                    notifyItemRangeChanged(currPosition, itemList.size());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class VH extends RecyclerView.ViewHolder{
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
