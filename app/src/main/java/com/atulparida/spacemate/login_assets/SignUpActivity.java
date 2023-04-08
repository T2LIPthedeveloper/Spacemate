package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BlendMode;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.atulparida.spacemate.R;

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