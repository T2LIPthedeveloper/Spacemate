package com.atulparida.spacemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class CommonLocationActivity extends AppCompatActivity {
    private Location bookingLoc;

    private ScrollView scrollView;

    private EditText name, teamName1, teamName2, teamName3, teamName4, teamName5;

    private TextView teamText1, teamText2, teamText3, teamText4, teamText5;
    //Radio groups
    private RadioGroup genderGroup, teamGroup;
    private RadioButton maleButton, femaleButton, otherButton;
    private RadioButton teamButton, individualButton;
    private Button bookButton, addTeamMemberButton;

    private LinearLayout hiddenLayout;
    private Booking booking;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            bookingLoc = (Location) bundle.getSerializable("location");
        }
        setContentView(R.layout.activity_common_location);

        initUI();
        initialiseValues();
        initListeners();
    }

    private void initListeners() {
        addTeamMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    teamText1.setVisibility(View.VISIBLE);
                    teamName1.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                }
                else if (i == 1) {
                    teamText2.setVisibility(View.VISIBLE);
                    teamName2.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                }
                else if (i == 2) {
                    teamText3.setVisibility(View.VISIBLE);
                    teamName3.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                }
                else if (i == 3) {
                    teamText4.setVisibility(View.VISIBLE);
                    teamName4.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                }
                else if (i == 4) {
                    teamText5.setVisibility(View.VISIBLE);
                    teamName5.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                }
                else {
                    Toast.makeText(CommonLocationActivity.this, "Maximum team size reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialiseValues() {
        hiddenLayout.setVisibility(View.GONE);
        addTeamMemberButton.setVisibility(View.GONE);
        teamName1.setVisibility(View.GONE);
        teamName2.setVisibility(View.GONE);
        teamName3.setVisibility(View.GONE);
        teamName4.setVisibility(View.GONE);
        teamName5.setVisibility(View.GONE);

        teamText1.setVisibility(View.GONE);
        teamText2.setVisibility(View.GONE);
        teamText3.setVisibility(View.GONE);
        teamText4.setVisibility(View.GONE);
        teamText5.setVisibility(View.GONE);
    }



    private void initUI() {
        booking = new Booking();
        hiddenLayout = findViewById(R.id.hiddenLayout);
        hiddenLayout.setVisibility(View.GONE);
        scrollView = findViewById(R.id.main_scroll_view);


        genderGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);
        otherButton = findViewById(R.id.otherButton);

        teamGroup = (RadioGroup) findViewById(R.id.teamRadioGroup);
        bookButton = findViewById(R.id.common_booking_button);
        addTeamMemberButton = findViewById(R.id.addTeamMemberButton);

        teamButton = findViewById(R.id.teamButton);
        individualButton = findViewById(R.id.individualButton);

        //EditText initialisation
        name = findViewById(R.id.name_of_booker);
        teamName1 = findViewById(R.id.team_name1);
        teamName2 = findViewById(R.id.team_name2);
        teamName3 = findViewById(R.id.team_name3);
        teamName4 = findViewById(R.id.team_name4);
        teamName5 = findViewById(R.id.team_name5);

        //TextView initialisation
        teamText1 = findViewById(R.id.team_text1);
        teamText2 = findViewById(R.id.team_text2);
        teamText3 = findViewById(R.id.team_text3);
        teamText4 = findViewById(R.id.team_text4);
        teamText5 = findViewById(R.id.team_text5);
    }

    public void onGenderRadioButtonClicked(View view) {
        RadioButton rb = (RadioButton) view;
        if (view.equals(maleButton)) {
            Toast.makeText(getApplicationContext(), "Gender male!", Toast.LENGTH_SHORT).show();
        }
        else if (view.equals(femaleButton)) {
            Toast.makeText(getApplicationContext(), "Gender female!", Toast.LENGTH_SHORT).show();
        }
        else if (view.equals(otherButton)) {
            Toast.makeText(getApplicationContext(), "Gender other!", Toast.LENGTH_SHORT).show();
        }
        else {
            throw new RuntimeException("Unknown radio button clicked!");
        }
    }

    public void onTeamRadioButtonClicked(View view) {
        RadioButton rb = (RadioButton) view;
        if (view.equals(teamButton)) {
            Toast.makeText(getApplicationContext(), "Team!", Toast.LENGTH_SHORT).show();
            hiddenLayout.setVisibility(View.VISIBLE);
            addTeamMemberButton.setVisibility(View.VISIBLE);
        }
        else if (view.equals(individualButton)) {
            Toast.makeText(getApplicationContext(), "Individual!", Toast.LENGTH_SHORT).show();
            hiddenLayout.setVisibility(View.GONE);
        }
        else {
            throw new RuntimeException("Unknown radio button clicked!");
        }
    }
}