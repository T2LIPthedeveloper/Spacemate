package com.atulparida.spacemate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private Booking[] itemData;

    public BookingAdapter(Booking[] itemData) {
        this.itemData = itemData;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View bookedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_template, parent, false);
        ViewHolder viewHolder = new ViewHolder(bookedView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder viewHolder, int position) {
        //TODO: replace with data from Firebase

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookingId;
        public TextView bookingDate;
        //TODO: replace with actual format of data

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }
}
