package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BlendMode;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atulparida.spacemate.MainActivity;
import com.atulparida.spacemate.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput;
    private Button signUpButton;

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
        setContentView(R.layout.activity_sign_up);

        initElements();
        signUpButton.setEnabled(false);
        signUpButton.setAlpha(0.5f);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid_name = checkNameValid();
                boolean valid_pwd = checkPwdValid();
                boolean valid_email = checkEmailValid();
                if (valid_name && valid_email && valid_pwd) {
                    Toast.makeText(getApplicationContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show();
                    //TODO: Pass data to user object

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else if (!valid_name) {
                    Toast.makeText(getApplicationContext(), "Invalid name!", Toast.LENGTH_SHORT).show();
                }
                else if (!valid_email) {
                    Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
                }
                else if (!valid_pwd) {
                    Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmailValid() {
        String target = emailInput.getText().toString().trim();
        if (target.equals(null))
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean checkPwdValid() {
        String password = passwordInput.getText().toString().trim();
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean checkNameValid() {
        if (nameInput.getText().toString().trim().equals(""))
            return false;
        return true;
    }

    private void initElements() {
        nameInput = findViewById(R.id.name_value);
        nameInput.addTextChangedListener(textWatcher);

        emailInput = findViewById(R.id.email_value);
        emailInput.addTextChangedListener(textWatcher);

        passwordInput = findViewById(R.id.pwd_value);
        passwordInput.addTextChangedListener(textWatcher);


        signUpButton = findViewById(R.id.sign_up_btn);
    }

    private void checkFieldsForEmptyValues(){
        String s1 = nameInput.getText().toString();
        String s2 = emailInput.getText().toString();
        String s3 = passwordInput.getText().toString();

        if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0) {
            signUpButton.setEnabled(true);
            signUpButton.setAlpha(1f);

        } else {
            signUpButton.setEnabled(false);
        }
    }
}