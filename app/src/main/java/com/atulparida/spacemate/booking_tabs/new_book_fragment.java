package com.atulparida.spacemate.booking_tabs;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atulparida.spacemate.Booking;
import com.atulparida.spacemate.DatabaseCall;
import com.atulparida.spacemate.Location;
import com.atulparida.spacemate.LocationAdapter;
import com.atulparida.spacemate.R;
import com.atulparida.spacemate.Table;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class new_book_fragment extends Fragment {
    public static final String TAG = "TAG";
    private RecyclerView recyclerView;
    private List<Location> locationArrayList;
    private List<Table> tableList;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_book_fragment, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = view.findViewById(R.id.newbooking_recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initData();
        //TODO: Make this firebase query a method instead
        db = FirebaseFirestore.getInstance();
        List<Table> tableList = new ArrayList<>();
        List<Location> locationArrayList = new ArrayList<>();
        List<Booking> bookingList = new ArrayList<>();
        CollectionReference Location = db.collection("Locations");
        Location.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                List<Long> tableMaxList = (List<Long>) document.get("tableMax");
                                long tableCount = document.getLong("noOfTables");
                                for(int i = 1; i<=(int)tableCount; i++){
                                    Table table = new Table("Table " + i, Math.toIntExact(tableMaxList.get(i - 1)));
                                    tableList.add(table);
                                }
                                Location l = new Location(document.getId(), tableList, R.drawable.img_albert_hong1);

                                //function to query bookings table
                                CollectionReference Bookings = db.collection("Bookings");
                                Date today = new Date();
                                Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
                                Bookings.whereEqualTo("location", document.getId())
                                        .whereGreaterThan("bookingDate", yesterday)
                                        .orderBy("bookingDate")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        Booking booking = document.toObject(Booking.class);
                                                        bookingList.add(booking);
                                                    }
                                                    l.setBookingList(bookingList);
                                                    locationArrayList.add(l);

                                                    LocationAdapter locationAdapter = new LocationAdapter(locationArrayList);

                                                    recyclerView.setAdapter(locationAdapter);
                                                    locationAdapter.notifyDataSetChanged();
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return view;
    }

//    public void setBookingList(String location, Location l) {
//
//    CollectionReference Location = db.collection("Bookings");
//    Date today = new Date();
//    Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
//    Location.whereEqualTo("location", location)
//            .whereGreaterThan("bookingDate", yesterday)
//                .orderBy("bookingDate")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Log.d(TAG, document.getId() + " => " + document.getData());
//                    Booking booking = document.toObject(Booking.class);
//                    bookingList.add(booking);
//                }
//                l.setBookingList(bookingList);
//                System.out.println(l);
//                locationArrayList.add(l);
//            } else {
//                Log.d(TAG, "Error getting documents: ", task.getException());
//            }
//        }
//    });
}

//    private void initData() {
//        locationArrayList = new ArrayList<Location>();
//        ArrayList<Table> tableList = new ArrayList<Table>();
//        tableList.add(new Table("Table 1", 4));
//        tableList.add(new Table("Table 2", 4));
//        tableList.add(new Table("Table 3", 4));
//        tableList.add(new Table("Table 4", 4));
//        tableList.add(new Table("Table 5", 4));
//        tableList.add(new Table("Table 6", 4));
//        tableList.add(new Table("Table 7", 4));
//        tableList.add(new Table("Table 8", 4));
//        tableList.add(new Table("Table 9", 4));
//        tableList.add(new Table("Table 10", 4));
//        tableList.add(new Table("Table 11", 4));
//        tableList.add(new Table("Table 12", 4));
//        tableList.add(new Table("Table 13", 4));
//        tableList.add(new Table("Table 14", 4));
//        tableList.add(new Table("Table 15", 4));
//
//
//        Location location = new Location("Outside Albert Hong LT", tableList, R.drawable.img_albert_hong1);
//        locationArrayList.add(location);
//    }

