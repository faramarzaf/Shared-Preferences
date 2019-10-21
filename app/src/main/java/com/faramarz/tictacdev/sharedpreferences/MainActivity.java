package com.faramarz.tictacdev.sharedpreferences;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faramarz.tictacdev.sharedpreferences.enum_package.GenderEnum;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.txtSelectDate)
    TextView txtSelectDate;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.rbFemale)
    RadioButton rbFemale;
    @BindView(R.id.rbMale)
    RadioButton rbMale;
    @BindView(R.id.rgProfile)
    RadioGroup rgProfile;

    SharedPreferences pref;
    private int mYear, mMonth, mDay;
    String maleGender = GenderEnum.MALE.getStringGender();
    String femaleGender = GenderEnum.FEMALE.getStringGender();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // below code for draw underline on select date textview
        txtSelectDate.setPaintFlags(txtSelectDate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        changeStatusBarColor();
        setSavedData();
    }


    @Override
    public void onClick(View v) {
        int id1 = v.getId();
        switch (id1) {
            case R.id.btn_login:
                performLoginClick();
                break;
            case R.id.txtSelectDate:
                getDate();
                break;
        }
    }

    private void setSavedData() {
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        btn_login.setOnClickListener(this);
        txtSelectDate.setOnClickListener(this);
        int id = rgProfile.getCheckedRadioButtonId();
        if (pref.contains("username") && rgProfile.getCheckedRadioButtonId() != -1) {
            edtUsername.setText(pref.getString("username", null));
            edtPassword.setText(pref.getString("password", null));
            if (id == R.id.rbMale) {
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
            } else {
                rbFemale.setChecked(true);
                rbMale.setChecked(true);
            }
        }
    }

    private void performLoginClick() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if (username.isEmpty() && password.isEmpty()) {
            showNullInputMessage();
        } else if (rgProfile.getCheckedRadioButtonId() != -1) {
            int id = rgProfile.getCheckedRadioButtonId();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", username);
            editor.putString("password", password);

            switch (id) {
                case R.id.rbMale:
                    editor.putString("gender", maleGender);
                    break;
                case R.id.rbFemale:
                    editor.putString("gender", femaleGender);
                    break;
            }
            editor.apply();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
    }

    private void getDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(MainActivity.this, "Your time is selected", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("day", dayOfMonth);
                editor.putInt("month", monthOfYear);
                editor.putInt("year", year);
                editor.apply();
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

    private void showNullInputMessage() {
        edtUsername.setError("Username is required!");
        edtUsername.requestFocus();
        edtPassword.setError("Password is required!");
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


