package com.atulparida.spacemate;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseCall {
    public static final String TAG = "TAG";
    public static void makeBooking(Booking booking, FirebaseFirestore db) {
        CollectionReference bookingCollection = db.collection("Bookings");
        DocumentReference bookingRef = bookingCollection.document();
        booking.setBookingId(bookingRef.getId());

        bookingRef.set(booking)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public static void deleteBooking(List<Booking> itemData, int index, FirebaseFirestore db) {
        String docref = itemData.get(index).getBookingId();
        db.collection("Bookings").document(docref)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Spacemate", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Spacemate", "Error deleting document", e);
                    }
                });
    }

    //currently called directly inside new_book_fragment
    public static List<Location> getLocationList(FirebaseFirestore db) {
        List<Table> tableList = new ArrayList<>();
        List<Location> locationArrayList = new ArrayList<>();
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
                                    Table table = new Table("Table" + i, Math.toIntExact(tableMaxList.get(i - 1)));
                                    tableList.add(table);
                                }
                                Location l = new Location(document.getId(), tableList, R.drawable.img_albert_hong1);
                                locationArrayList.add(l);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return locationArrayList;
    }

    public static List<Booking> getBookingList(String location, FirebaseFirestore db) {
        List<Booking> bookingList = new ArrayList<>();
        CollectionReference Location = db.collection("Bookings");
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
        Location.whereEqualTo("location", location)
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
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return bookingList;
    }
}
