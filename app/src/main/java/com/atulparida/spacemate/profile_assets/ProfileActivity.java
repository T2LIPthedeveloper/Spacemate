package com.atulparida.spacemate.profile_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<UserListView> itemsList;
    private List<String> userDataList;
    int[] userDataIcons = {R.drawable.baseline_account_circle_24, R.drawable.baseline_menu_book_24, R.drawable.baseline_school_24, R.drawable.baseline_home_24, R.drawable.baseline_people_24};
    String[] headings = {"Email", "Term Information", "Pillar", "Hostel Residency", "Gender"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listView = (ListView) findViewById(R.id.profile_list_view);

        initUserData();

        ProfileViewAdapter profileViewAdapter = new ProfileViewAdapter(this, R.layout.profile_list_template, itemsList);
        listView.setAdapter(profileViewAdapter);


    }

    private void initUserData() {
        //TODO: replace with Firebase GET from API
        //Sample data
        userDataList.add("person_doe@proton.net");
        userDataList.add("Term 4");
        userDataList.add("CSD");
        userDataList.add("Yes");
        userDataList.add("Female");

        for (int i = 0; i < 5; i++) {
            itemsList.add(new UserListView(userDataIcons[i], headings[i], userDataList.get(i)));
        }
    }
}