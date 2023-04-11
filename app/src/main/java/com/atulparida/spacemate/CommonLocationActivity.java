package com.atulparida.spacemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.atulparida.spacemate.booking_tabs.upcoming_fragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonLocationActivity extends AppCompatActivity {

    Calendar myCalendar;
    private Location bookingLoc;

    private ScrollView scrollView;

    private EditText name, teamName1, teamName2, teamName3, teamName4, teamName5;

    private TextView heading, teamText1, teamText2, teamText3, teamText4, teamText5;

    private TextView pickDate, pickStartTime, pickEndTime;
    //Radio groups
    private RadioGroup genderGroup, teamGroup;
    private RadioButton maleButton, femaleButton, otherButton;
    private RadioButton teamButton, individualButton;
    private Button bookButton, addTeamMemberButton;

    private LinearLayout hiddenLayout;
    private Booking booking = new Booking();

    private Spinner tableSpinner;

    private DatePickerDialog.OnDateSetListener setDateListener;

    private FirebaseFirestore db;

    int i = 0, val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myCalendar = Calendar.getInstance();
        if (bundle != null) {
            bookingLoc = (Location) bundle.getSerializable("location");
        }
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_common_location);
        booking.setBookingId(String.valueOf((Math.random() * 1000000 + 100000)));
        initUI();
        initialiseValues();
        initListeners();
        //Table Spinner
        TableAdapter tableAdapter = new TableAdapter(this, R.layout.dropdown_menu_item, bookingLoc.getTableList());
        tableSpinner.setAdapter(tableAdapter);
        tableSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        // It returns the clicked item.
                        Table clickedItem = (Table)
                                parent.getItemAtPosition(position);
                        String name = clickedItem.getTableName();
                        Toast.makeText(CommonLocationActivity.this, name + " selected", Toast.LENGTH_SHORT).show();
                        int tableNo = Integer.parseInt(name.substring(5).trim());
                        booking.setTableNo(tableNo);
                        booking.setCapacity(clickedItem.getCapacity());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        //Date Spinner
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int day = myCalendar.get(Calendar.DAY_OF_MONTH);
                final int month = myCalendar.get(Calendar.MONTH);
                final int year = myCalendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CommonLocationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setDateListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });

        setDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String sel_date;
                month = month + 1;
                if (month < 10) {
                    sel_date = dayOfMonth + "/0" + month + "/" + year;
                }
                else {
                    sel_date = dayOfMonth + "/" + month + "/" + year;
                }
                pickDate.setText(sel_date);
                pickDate.setTextColor(Color.parseColor("#000000"));
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date bookingDate = new Date();
                try {
                    bookingDate = formatter.parse(pickDate.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                booking.setBookingDate(bookingDate);
            }
        };
        //Start Time Spinner
        pickStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(pickStartTime);
            }
        });
        //End Time Spinner
        pickEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(pickEndTime);
            }
        });
    }

    private void openDialog(TextView text) {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (i < 10) {
                    if (i1 < 10) {
                        text.setText("0" + String.valueOf(i) + ":0" + String.valueOf(i1));
                    }
                    else {
                        text.setText("0" + String.valueOf(i) + ":" + String.valueOf(i1));
                    }
                }
                else {
                    if (i1 < 10) {
                        text.setText(String.valueOf(i) + ":0" + String.valueOf(i1));
                    }
                    else {
                        text.setText(String.valueOf(i) + ":" + String.valueOf(i1));
                    }
                }
                text.setTextColor(Color.parseColor("#000000"));
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                try {
                    date = formatter.parse(text.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (text.equals(pickStartTime)) {
                    booking.setStartTime(date);
                } else {
                    booking.setEndTime(date);
                }
            }
        }, 12, 0, true);
        dialog.show();

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
                } else if (i == 1) {
                    teamText2.setVisibility(View.VISIBLE);
                    teamName2.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                } else if (i == 2) {
                    teamText3.setVisibility(View.VISIBLE);
                    teamName3.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                } else if (i == 3) {
                    teamText4.setVisibility(View.VISIBLE);
                    teamName4.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                } else if (i == 4) {
                    teamText5.setVisibility(View.VISIBLE);
                    teamName5.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                    i++;
                } else {
                    Toast.makeText(CommonLocationActivity.this, "Maximum team size reached", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (individualButton.isChecked()) {
                    booking.setBookedNo(1);
                }
                else if (teamButton.isChecked()) {
                    booking.setBookedNo(i+1);
                }
                if (booking.getBookedNo() == 0) {
                    Toast.makeText(CommonLocationActivity.this, "Please select booking type", Toast.LENGTH_SHORT).show();
                }
                else if (booking.getBookingDate() == null) {
                    Toast.makeText(CommonLocationActivity.this, "Please select date", Toast.LENGTH_SHORT).show();
                }
                else if (booking.getStartTime() == null) {
                    Toast.makeText(CommonLocationActivity.this, "Please select start time", Toast.LENGTH_SHORT).show();
                }
                else if (booking.getEndTime() == null) {
                    Toast.makeText(CommonLocationActivity.this, "Please select end time", Toast.LENGTH_SHORT).show();
                }
                else if (booking.getTableNo() == 0) {
                    Toast.makeText(CommonLocationActivity.this, "Please select table", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("booking", booking);
                    makeBooking();
                    Intent intent = new Intent(CommonLocationActivity.this, MainActivity.class);

//                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
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
        heading = findViewById(R.id.cl_locTitle);
        heading.setText(bookingLoc.getName());

        teamText1 = findViewById(R.id.team_text1);
        teamText2 = findViewById(R.id.team_text2);
        teamText3 = findViewById(R.id.team_text3);
        teamText4 = findViewById(R.id.team_text4);
        teamText5 = findViewById(R.id.team_text5);

        //Spinner initialisation
        tableSpinner = findViewById(R.id.tableSpinner);
        pickDate = findViewById(R.id.dateSelect);
        pickStartTime = findViewById(R.id.startTimeSelect);
        pickEndTime = findViewById(R.id.endTimeSelect);
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
            initialiseValues();
            i = 0;
        }
        else {
            throw new RuntimeException("Unknown radio button clicked!");
        }
    }

    public void makeBooking() {
        CollectionReference bookingCollection = db.collection("Bookings");
        DocumentReference bookingRef = bookingCollection.document();
        booking.setName(name.getText().toString());
        booking.setBookingId(bookingRef.getId());

        bookingRef.set(booking)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Spacemate", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Spacemate", "Error writing document", e);
                    }
                });
    }
}

class TableAdapter extends ArrayAdapter<Table> {
    private Context context;
    private ArrayList<Table> tables;

    public TableAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Table> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.tables = (ArrayList<Table>) objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_menu_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textview);
        Table currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getTableName());
        }
        return convertView;
    }
}