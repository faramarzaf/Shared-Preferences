package com.faramarz.tictacdev.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.txt_username)
    EditText txt_username;
    @BindView(R.id.txt_pass)
    EditText txt_pass;
    @BindView(R.id.btn_login)
    Button btn_login;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        changeStatusBarColor();
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        btn_login.setOnClickListener(this);

        if (pref.contains("username")) {
            txt_username.setText(pref.getString("username", null));
        }

    }

    @Override
    public void onClick(View v) {
        String username = txt_username.getText().toString();
        String password = txt_pass.getText().toString();
        if (username.isEmpty() && password.isEmpty()) {
            showNullInputMesssage();
        } else {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

    }

    private void showNullInputMesssage() {
        txt_username.setError("Username is required!");
        txt_username.requestFocus();
        txt_pass.setError("Password is required!");
        txt_pass.requestFocus();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}


