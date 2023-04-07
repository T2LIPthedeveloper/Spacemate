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
    private Button LoginButton, SignUpButton;
    private TextView ForgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailEditText = findViewById(R.id.login_email);
        PasswordEditText = findViewById(R.id.login_pwd);
        LoginButton = findViewById(R.id.login_btn);
        ForgotPasswordTextView = findViewById(R.id.forgot_pwd_btn);
        SignUpButton = findViewById(R.id.signup_btn);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Authenticate user with email and password
                authenticateUser();
            }
        });
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
        //TODO: replace with Google user authentication
    }


}