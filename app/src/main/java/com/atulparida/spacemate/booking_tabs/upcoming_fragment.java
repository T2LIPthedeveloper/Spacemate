package com.atulparida.spacemate.booking_tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atulparida.spacemate.Booking;
import com.atulparida.spacemate.BookingAdapter;
import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class upcoming_fragment extends Fragment {
    private RecyclerView recyclerView;
    List<Booking> bookedList;

    private Bundle bundle;
    private FirebaseFirestore db;
    private FirebaseAuth fAuth;
    private String userEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userEmail = fAuth.getCurrentUser().getEmail();
        //get bundle argument
        Bundle b = getArguments();
        this.bundle = b;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_fragment, container, false);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


         db.collection("Bookings")
                 .whereEqualTo("name", userEmail)
                .whereGreaterThan("bookingDate", new Date())
                .orderBy("bookingDate")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                bookedList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("Spacemate", document.getId() + " => " + document.getData());
                    Booking booking = document.toObject(Booking.class);
                    bookedList.add(booking);
                }
                recyclerView = view.findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                BookingAdapter bookingAdapter = new BookingAdapter(bookedList);

                recyclerView.setAdapter(bookingAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                bookingAdapter.notifyDataSetChanged();
            } else {
                Log.d("Spacemate", "Error getting documents: ", task.getException());
            }
        }
    });
         return view;

    }

}