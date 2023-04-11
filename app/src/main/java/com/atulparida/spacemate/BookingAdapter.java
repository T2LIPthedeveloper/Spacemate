package com.atulparida.spacemate;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.atulparida.spacemate.booking_tabs.booking_fragment;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    List<Booking> itemData;

    public BookingAdapter(List<Booking> itemData) {
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
        Booking booking = itemData.get(position);
        viewHolder.capacityNo.setText(booking.parseCapacity());
        viewHolder.dateOfBooking.setText(booking.parseDate());
        viewHolder.tableNo.setText(booking.parseTable());
        viewHolder.timingValues.setText(booking.parseTimings());
        boolean isVisible = booking.isVisibility();
        viewHolder.relativeLayout.setVisibility(isVisible? View.VISIBLE : View.GONE);
        if (isVisible) {
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            viewHolder.imageButton.setImageResource(R.drawable.baseline_expand_less_24);
        }
        else {
            viewHolder.relativeLayout.setVisibility(View.GONE);
            viewHolder.imageButton.setImageResource(R.drawable.baseline_expand_more_24);
        }

        viewHolder.bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking_fragment bookingMenuDialog = new booking_fragment();
                bookingMenuDialog.show(((FragmentActivity) viewHolder.itemView.getContext()).getSupportFragmentManager(), "BookingMenuDialog");
            }
        });



    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tableNo;
        TextView capacityNo;
        TextView timingValues;
        TextView dateOfBooking;

        RelativeLayout relativeLayout;

        Button bookNowButton;

        ImageButton imageButton;
        //TODO: replace with actual format of data

        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.expanded_layout);
            tableNo = itemView.findViewById(R.id.table_no);
            capacityNo = itemView.findViewById(R.id.capacity_no);
            timingValues = itemView.findViewById(R.id.time);
            dateOfBooking = itemView.findViewById(R.id.date);
            imageButton = itemView.findViewById(R.id.expand_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Booking book = itemData.get(getAdapterPosition());
                    book.setVisibility(!book.isVisibility());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            bookNowButton = itemView.findViewById(R.id.book_now_button);
        }
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }
}
