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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.rbFemale)
    RadioButton rbFemale;
    @BindView(R.id.rbMale)
    RadioButton rbMale;
    @BindView(R.id.rgProfile)
    RadioGroup rgProfile;


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
            txtUsername.setText(pref.getString("username", null));
            txtPassword.setText(pref.getString("password", null));

        }

        rgProfile.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rgProfile.getCheckedRadioButtonId() != -1) {
                    int id = rgProfile.getCheckedRadioButtonId();
                    View radioButton = rgProfile.findViewById(id);
                    int radioId = rgProfile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) rgProfile.getChildAt(radioId);
                    String selection = (String) btn.getText();


                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if (username.isEmpty() && password.isEmpty()) {
            showNullInputMessage();
        } else if (rgProfile.getCheckedRadioButtonId() != -1) {
            int id = rgProfile.getCheckedRadioButtonId();
            View radioButton = rgProfile.findViewById(id);
            int radioId = rgProfile.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgProfile.getChildAt(radioId);
            String gender = (String) btn.getText();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("gender", gender);
            editor.apply();
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }

    }

    private void showNullInputMessage() {
        txtUsername.setError("Username is required!");
        txtUsername.requestFocus();
        txtPassword.setError("Password is required!");
        txtPassword.requestFocus();
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


