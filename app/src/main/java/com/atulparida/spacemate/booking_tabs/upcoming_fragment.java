package com.atulparida.spacemate.booking_tabs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atulparida.spacemate.Booking;
import com.atulparida.spacemate.BookingAdapter;
import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class upcoming_fragment extends Fragment {
    private RecyclerView recyclerView;
    List<Booking> bookedList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_fragment, container, false);




        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        //TODO: replace initData data with call to Firebase to get data
        initData();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);




        BookingAdapter bookingAdapter = new BookingAdapter(bookedList);

        recyclerView.setAdapter(bookingAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingAdapter.notifyDataSetChanged();





        return view;

    }

    private void initData() {
        bookedList = new ArrayList<>();
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        bookedList.add(new Booking("1000abcd", 2, new Date(), new Date(), new Date(), 4, 5, false));
        }
}