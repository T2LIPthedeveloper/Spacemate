package com.atulparida.spacemate.profile_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private EditText term, pillar, hostel, gender;
    private ImageView termEdit, pillarEdit, hostelEdit, genderEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initEditTexts();
        initImageViews();
        initOnClickListeners();
    }

    private void initOnClickListeners() {

        termEdit.setOnClickListener(v -> {
            if (term.isEnabled()) {
                setViewingMode(term);
            } else {
                setEditingMode(term, this);
            }
        });

        pillarEdit.setOnClickListener(v -> {
            if (pillar.isEnabled()) {
                setViewingMode(pillar);
            } else {
                setEditingMode(pillar, this);
            }
        });

        hostelEdit.setOnClickListener(v -> {
            if (hostel.isEnabled()) {
                setViewingMode(hostel);
            } else {
                setEditingMode(hostel, this);
            }
        });

        genderEdit.setOnClickListener(v -> {
            if (gender.isEnabled()) {
                setViewingMode(gender);
            } else {
                setEditingMode(gender, this);
            }
        });
    }

    private void initImageViews() {
        termEdit = findViewById(R.id.termEdit);
        pillarEdit = findViewById(R.id.pillarEdit);
        hostelEdit = findViewById(R.id.hostelEdit);
        genderEdit = findViewById(R.id.genderEdit);
    }

    private void initEditTexts() {
        term = findViewById(R.id.termValue);
        pillar = findViewById(R.id.pillarValue);
        hostel = findViewById(R.id.hostelValue);
        gender = findViewById(R.id.genderValue);
    }

    public static void setViewingMode(final EditText mEditView) {
        mEditView.setEnabled(false);
        mEditView.setText(mEditView.getText().toString().trim());
        }

    public static void setEditingMode(final EditText mEditView, final Context context) {
        mEditView.setEnabled(true);
        mEditView.requestFocus();
        mEditView.setSelection(mEditView.getText().length());
        }
}