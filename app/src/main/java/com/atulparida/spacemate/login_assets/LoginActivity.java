package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.atulparida.spacemate.MainActivity;
import com.atulparida.spacemate.R;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class LoginActivity extends AppCompatActivity {

    private EditText EmailEditText, PasswordEditText;
    private Button LoginButton, SignUpButton;
    private TextView ForgotPasswordTextView;

    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailEditText = findViewById(R.id.login_email);
        EmailEditText.addTextChangedListener(textWatcher);

        PasswordEditText = findViewById(R.id.login_pwd);
        PasswordEditText.addTextChangedListener(textWatcher);

        LoginButton = findViewById(R.id.login_btn);
        ForgotPasswordTextView = findViewById(R.id.forgot_pwd_btn);
        SignUpButton = findViewById(R.id.signup_btn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Authenticate user with email and password
                authenticateUserWithGoogle();
            }
        });
        LoginButton.setEnabled(false);
        LoginButton.setAlpha(0.5f);

        ForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open forgot password screen
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open sign up screen
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
    private void authenticateUser() {
            // Get email and password from input fields
            String email = EmailEditText.getText().toString().trim();
            String password = PasswordEditText.getText().toString().trim();
        }

    private void authenticateUserWithGoogle() {

        String email = EmailEditText.getText().toString().trim();
        String password = PasswordEditText.getText().toString().trim();
        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

            private void checkFieldsForEmptyValues() {
                String s1 = EmailEditText.getText().toString();
                String s2 = PasswordEditText.getText().toString();

                if (s1.length() > 0 && s2.length() > 0) {
                    LoginButton.setEnabled(true);
                    LoginButton.setAlpha(1f);

                } else {
                    LoginButton.setEnabled(false);
                }
            }


        }