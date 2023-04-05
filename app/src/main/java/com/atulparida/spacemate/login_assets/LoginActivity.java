package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.atulparida.spacemate.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText EmailEditText, PasswordEditText;
    private Button LoginButton;
    private TextView ForgotPasswordTextView, SignUpTextView, GoogleSignInTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailEditText = findViewById(R.id.email_edit_text);
        PasswordEditText = findViewById(R.id.password_edit_text);
        LoginButton = findViewById(R.id.login_button);
        ForgotPasswordTextView = findViewById(R.id.forgot_password_text_view);
        SignUpTextView = findViewById(R.id.sign_up_text_view);
        GoogleSignInTextView = findViewById(R.id.google_sign_in_text_view);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Authenticate user with email and password
                authenticateUser();
            }
        });
        mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open forgot password screen
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        SignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open sign up screen
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Authenticate user with Google
                authenticateUserWithGoogle();
            }
        });
        private void authenticateUser() {
            // Get email and password from input fields
            String email = mEmailEditText.getText().toString().trim();
            String password = mPasswordEditText.getText().toString().trim();

            // Authenticate user with email and password
        }

        private void authenticateUserWithGoogle() {
            // Authenticate user with Google
        }


}