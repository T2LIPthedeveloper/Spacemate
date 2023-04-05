package com.atulparida.spacemate.profile_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private List userDataList;
    int[] userDataIcons = {R.drawable.baseline_account_circle_24, R.drawable.baseline_menu_book_24, R.drawable.baseline_school_24, R.drawable.baseline_home_24, R.drawable.baseline_people_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listView = (ListView) findViewById(R.id.profile_list_view);


    }
}