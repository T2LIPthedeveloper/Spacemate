package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.atulparida.spacemate.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText newPasswordET, confirmPasswordET;
    private Button forgotPasswordLoginButton;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initElements();

        forgotPasswordLoginButton.setEnabled(false);
        forgotPasswordLoginButton.setAlpha(0.5f);
    }

    private void initElements() {
        newPasswordET = findViewById(R.id.pwdValue);
        newPasswordET.addTextChangedListener(textWatcher);

        confirmPasswordET = findViewById(R.id.confirmPwdValue);
        confirmPasswordET.addTextChangedListener(textWatcher);

        forgotPasswordLoginButton = findViewById(R.id.fp_login_btn);

    }

    private void checkFieldsForEmptyValues() {
        String s1 = newPasswordET.getText().toString();
        String s2 = confirmPasswordET.getText().toString();

        if (s1.length() > 0 && s2.length() > 0) {
            forgotPasswordLoginButton.setEnabled(true);
            forgotPasswordLoginButton.setAlpha(1f);

        } else {
            forgotPasswordLoginButton.setEnabled(false);
            forgotPasswordLoginButton.setAlpha(0.5f);
        }
    }
}