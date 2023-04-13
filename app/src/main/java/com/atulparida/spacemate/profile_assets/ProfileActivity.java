package com.atulparida.spacemate.profile_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;

import com.atulparida.spacemate.MainActivity;
import com.atulparida.spacemate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText term, pillar, hostel, gender;
    private TextView nameViewer, emailViewer, termViewer, pillarViewer, hostelViewer, genderViewer;
    private ViewSwitcher termVS, pillarVS, hostelVS, genderVS;
    private ImageView termEdit, pillarEdit, hostelEdit, genderEdit;

    private Button saveButton;

    private InputMethodManager imm;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    FirebaseUser user;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        documentReference = fStore.collection("users").document(fAuth.getCurrentUser().getUid());
        user = fAuth.getCurrentUser();
        initEditTexts();
        initTextViews();
        initVS();
        initImageViews();
        saveButton = findViewById(R.id.saveButton);
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
        nameViewer = findViewById(R.id.nameValue);
        DocumentReference documentReference = fStore.collection("users").document(fAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    nameViewer.setText(documentSnapshot.getString("fName"));
                    termViewer.setText(documentSnapshot.getString("term"));
                    hostelViewer.setText(documentSnapshot.getString("hostel"));
                    genderViewer.setText(documentSnapshot.getString("gender"));
                    pillarViewer.setText(documentSnapshot.getString("pillar"));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        //TODO: get name from Firestore instance based on user value
        emailViewer = findViewById(R.id.emailValue);
        emailViewer.setText(fAuth.getCurrentUser().getEmail());
    }

    private void initOnClickListener(ImageView itemEdit, TextView textViewer, ViewSwitcher vs, EditText text) {
        itemEdit.setOnClickListener(v -> {
            if (!text.isEnabled()) {
                vs.showNext();
                text.setEnabled(true);

                if (term.equals(text)) {
                    text.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                    text.setHint("1 to 8 for term, 9 for alumni");
                } else if (pillar.equals(text)) {
                    text.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    text.setHint("ASD, CSD, DAI, EPD, or ESD");
                } else if (hostel.equals(text)) {
                    text.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                    text.setHint("Yes or No");
                } else if (gender.equals(text)) {
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
                        if (!text.getText().toString().isEmpty() && (Integer.parseInt(text.getText().toString()) > 0 && Integer.parseInt(text.getText().toString()) < 9)) {
                            textViewer.setText(("Term " + term.getText().toString().trim()).trim());
                            term.setText(("Term " + term.getText().toString().trim()).trim());
                        } else if (Integer.parseInt(term.getText().toString()) == 9) {
                            textViewer.setText("Alumni");
                            term.setText("Alumni");
                        }
                    } else if (pillar.equals(text)) {
                        if (text.getText().toString().trim().equals("ASD") || text.getText().toString().trim().equals("CSD") || text.getText().toString().trim().equals("EPD") || text.getText().toString().trim().equals("ESD") || text.getText().toString().trim().equals("DAI")) {
                            textViewer.setText(text.getText().toString().trim());
                            pillar.setText(text.getText().toString().trim());
                        }
                    } else if (hostel.equals(text)) {
                        if (text.getText().toString().trim().equals("Yes") || text.getText().toString().trim().equals("No")) {
                            textViewer.setText(text.getText().toString().trim());
                            hostel.setText(text.getText().toString().trim());
                        }
                    } else if (gender.equals(text)) {
                        if (text.getText().toString().trim().equals("Male") || text.getText().toString().trim().equals("Female") ||  text.getText().toString().length() <= 7 || text.getText().toString().trim().equals("Other") )  {
                            textViewer.setText(text.getText().toString().trim());
                            gender.setText(text.getText().toString().trim());
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(ProfileActivity.this, "Invalid value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference docRef = fStore.collection("users").document(user.getUid());
                Map<String, Object> edited = new HashMap<>();

                edited.put("term", term.getText().toString());
                edited.put("pillar", pillar.getText().toString());
                edited.put("hostel", hostel.getText().toString());
                edited.put("gender", gender.getText().toString());
                docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }

        });

    }

    private void initEditTexts() {
        term = findViewById(R.id.termValue);
        pillar = findViewById(R.id.pillarValue);
        hostel = findViewById(R.id.hostelValue);
        gender = findViewById(R.id.genderValue);

        DocumentReference documentReference = fStore.collection("users").document(fAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    term.setText(documentSnapshot.getString("term"));
                    hostel.setText(documentSnapshot.getString("hostel"));
                    gender.setText(documentSnapshot.getString("gender"));
                    pillar.setText(documentSnapshot.getString("pillar"));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

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

