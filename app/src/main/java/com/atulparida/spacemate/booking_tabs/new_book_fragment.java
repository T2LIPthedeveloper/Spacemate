package com.atulparida.spacemate.booking_tabs;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atulparida.spacemate.Location;
import com.atulparida.spacemate.LocationAdapter;
import com.atulparida.spacemate.R;
import com.atulparida.spacemate.Table;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class new_book_fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Location> locationArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_book_fragment, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        initData();

        recyclerView = view.findViewById(R.id.newbooking_recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LocationAdapter locationAdapter = new LocationAdapter(locationArrayList);

        recyclerView.setAdapter(locationAdapter);
        locationAdapter.notifyDataSetChanged();
        return view;
    }

    private void initData() {
        locationArrayList = new ArrayList<Location>();
        ArrayList<Table> tableList = new ArrayList<Table>();
        tableList.add(new Table("Table 1", 4));
        tableList.add(new Table("Table 2", 4));
        tableList.add(new Table("Table 3", 4));
        tableList.add(new Table("Table 4", 4));
        tableList.add(new Table("Table 5", 4));
        tableList.add(new Table("Table 6", 4));
        tableList.add(new Table("Table 7", 4));
        tableList.add(new Table("Table 8", 4));
        tableList.add(new Table("Table 9", 4));
        tableList.add(new Table("Table 10", 4));
        tableList.add(new Table("Table 11", 4));
        tableList.add(new Table("Table 12", 4));
        tableList.add(new Table("Table 13", 4));
        tableList.add(new Table("Table 14", 4));
        tableList.add(new Table("Table 15", 4));


        Location location = new Location("Outside Albert Hong LT", tableList, null);
        locationArrayList.add(location);
    }
}

