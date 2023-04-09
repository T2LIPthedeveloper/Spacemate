package com.atulparida.spacemate.booking_tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atulparida.spacemate.Location;
import com.atulparida.spacemate.R;

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
        initData();

        return view;
    }

    private void initData() {
        locationArrayList = new ArrayList<Location>();
    }
}