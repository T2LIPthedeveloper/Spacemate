package com.atulparida.spacemate.booking_tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atulparida.spacemate.FavouriteAdapter;
import com.atulparida.spacemate.Location;
import com.atulparida.spacemate.LocationAdapter;
import com.atulparida.spacemate.R;

import java.util.ArrayList;


public class bookmarks_fragment extends Fragment {

    private ArrayList<Location> favouritesList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmarks_fragment, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.bookmarks_recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initData();

        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(favouritesList);

        recyclerView.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();
        return view;
    }

    private void initData() {
        favouritesList = new ArrayList<Location>();
    }
}