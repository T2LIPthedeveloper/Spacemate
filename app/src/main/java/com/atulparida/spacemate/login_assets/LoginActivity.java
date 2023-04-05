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
    private TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}