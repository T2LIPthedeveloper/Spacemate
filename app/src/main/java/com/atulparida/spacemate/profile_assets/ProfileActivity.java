package com.atulparida.spacemate.profile_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private EditText term, pillar, hostel, gender;
    private TextView termViewer, pillarViewer, hostelViewer, genderViewer;
    private ViewSwitcher termVS, pillarVS, hostelVS, genderVS;
    private ImageView termEdit, pillarEdit, hostelEdit, genderEdit;

    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        initEditTexts();
        initTextViews();
        initVS();
        initImageViews();
        initOnClickListeners();
    }

    private void initVS() {
        termVS = findViewById(R.id.term_vs);
        pillarVS = findViewById(R.id.pillar_vs);
        hostelVS = findViewById(R.id.hostel_vs);
        genderVS = findViewById(R.id.gender_vs);
    }

    private void initTextViews() {
        termViewer = findViewById(R.id.termValueViewer);
        pillarViewer = findViewById(R.id.pillarValueViewer);
        hostelViewer = findViewById(R.id.hostelValueViewer);
        genderViewer = findViewById(R.id.genderValueViewer);
    }

    private void initOnClickListener(ImageView itemEdit, TextView textViewer, ViewSwitcher vs, EditText text) {
        itemEdit.setOnClickListener(v -> {
            if (!text.isEnabled()) {
                vs.showNext();
                text.setEnabled(true);

                if (term.equals(text)) {
                    text.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                    text.setHint("1 to 8 for term, 9 for alumni");
                }
                else if (pillar.equals(text)) {
                    text.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    text.setHint("ASD, CSD, DAI, EPD, or ESD");
                }
                else if (hostel.equals(text)) {
                    text.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    text.setHint("Yes or No");
                }
                else if (gender.equals(text)) {
                    text.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    text.setHint("Male, Female, Other");
                }
                text.setText("");
                text.requestFocus();
                imm.showSoftInput(text, InputMethodManager.SHOW_FORCED);
            } else {
                vs.showPrevious();
                text.setEnabled(false);
                try {
                    if (term.equals(text)) {
                        if (!text.getText().toString().isEmpty() && (Integer.parseInt(text.getText().toString()) > 0 && Integer.parseInt(text.getText().toString()) < 8)) {
                            textViewer.setText(("Term " + term.getText().toString().trim()).trim());
                        }
                        else if (Integer.parseInt(term.getText().toString()) == 9) {
                            textViewer.setText("Alumni");
                        }
                    }
                    else if (pillar.equals(text)) {
                        if (text.getText().toString().trim() == "ASD" || text.getText().toString().trim() == "CSD" || text.getText().toString().trim() == "EPD" || text.getText().toString().trim() == "ESD" || text.getText().toString().trim() == "DAI") {
                            textViewer.setText(text.getText().toString().trim());
                        }
                    }
                    else if (hostel.equals(text)) {
                        if (text.getText().toString().trim() == "Yes" || text.getText().toString().trim() == "No") {
                            textViewer.setText(text.getText().toString().trim());
                        }
                    }
                    else if (gender.equals(text)) {
                        if (text.getText().toString().trim() == "Male" || text.getText().toString().trim() == "Female" || text.getText().toString().trim() == "Other") {
                            textViewer.setText(text.getText().toString().trim());
                        }
                    }
                }
                catch (Exception e) {
                    Toast.makeText(ProfileActivity.this, "Invalid value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initEditTexts() {
        term = findViewById(R.id.termValue);
        pillar = findViewById(R.id.pillarValue);
        hostel = findViewById(R.id.hostelValue);
        gender = findViewById(R.id.genderValue);

        term.setEnabled(false);
        pillar.setEnabled(false);
        hostel.setEnabled(false);
        gender.setEnabled(false);
    }

    private void initOnClickListeners() {
        initOnClickListener(termEdit, termViewer, termVS, term);
        initOnClickListener(pillarEdit, pillarViewer, pillarVS, pillar);
        initOnClickListener(hostelEdit, hostelViewer, hostelVS, hostel);
        initOnClickListener(genderEdit, genderViewer, genderVS, gender);
    }



    private void initImageViews() {
        termEdit = findViewById(R.id.termEdit);
        pillarEdit = findViewById(R.id.pillarEdit);
        hostelEdit = findViewById(R.id.hostelEdit);
        genderEdit = findViewById(R.id.genderEdit);
    }
}

