package com.faramarz.tictacdev.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences pref;

    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtPass)
    TextView txtPass;
    @BindView(R.id.txtGen)
    TextView txtGen;
    @BindView(R.id.txtDate)
    TextView txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        changeStatusBarColor();
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        txtName.setText("Name: " + pref.getString("username", null));
        txtPass.setText("Password: " + pref.getString("password", null));
        txtGen.setText("Gender: " + pref.getString("gender", null));
        txtDate.setText("Selected date is: "+pref.getInt("day",0)+"-"+pref.getInt("month",0)+"-"+pref.getInt("year",0));
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
