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
import com.atulparida.spacemate.BookmarkAdapter;
import com.atulparida.spacemate.R;
import com.atulparida.spacemate.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class bookmark_fragment extends Fragment {
    private RecyclerView recyclerView;
    List<Bookmark> bookmarkList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bookmark_fragment, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        //TODO: replace initData data with call to Firebase to get data
        initData();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(bookmarkList);

        recyclerView.setAdapter(bookmarkAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookmarkAdapter.notifyDataSetChanged();

        return view;
    }

    private void initData() {
        bookmarkList = new ArrayList<>();
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 1", "Sample Description 1", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 2", "Sample Description 2", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 3", "Sample Description 3", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 4", "Sample Description 4", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 5", "Sample Description 5", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 6", "Sample Description 6", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 7", "Sample Description 7", R.drawable.logo));
        bookmarkList.add(new Bookmark("1000abcd", "Sample Title 8", "Sample Description 8", R.drawable.logo));
    }
}
