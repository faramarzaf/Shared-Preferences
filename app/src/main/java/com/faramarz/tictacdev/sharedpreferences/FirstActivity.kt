package com.faramarz.tictacdev.sharedpreferences

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faramarz.tictacdev.sharedpreferences.enum_package.EnumGender
import kotlinx.android.synthetic.main.activity_first.*
import java.util.*

class FirstActivity : AppCompatActivity() {

    private var pref: SharedPreferences? = null
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private val maleGender = EnumGender.MALE.valueStr
    private val femaleGender = EnumGender.FEMALE.valueStr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        txtSelectDate!!.paintFlags = txtSelectDate!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        changeStatusBarColor()
        btn_login!!.setOnClickListener {
            performLoginClick()
        }
        txtSelectDate!!.setOnClickListener {
            getDate()
        }
        setSavedData()
    }


    private fun setSavedData() {
        pref = getSharedPreferences("user_details", Context.MODE_PRIVATE)
        val id = rgProfile!!.checkedRadioButtonId
        if (with(pref) { this!!.contains("username") } && with(pref) { this!!.contains("gender") }) {
            edtUsername!!.setText(with(pref) { this!!.getString("username", null) })
            edtPassword!!.setText(with(pref) { this!!.getString("password", null) })
            if (id == R.id.rbMale) {
                rbMale!!.isChecked = true
            } else if (id == R.id.rbFemale) {
                rbFemale!!.isChecked = true
            }
        }
    }


    private fun performLoginClick() {
        val username = edtUsername?.text.toString()
        val password = edtPassword?.text.toString()
        if (username.isEmpty() && password.isEmpty()) {
            showNullInputMessage()
        } else if (rgProfile!!.checkedRadioButtonId != -1) {
            val id = rgProfile!!.checkedRadioButtonId
            val editor = pref!!.edit()
            editor.putString("username", username)
            editor.putString("password", password)

            when (id) {
                R.id.rbMale -> editor.putString("gender", maleGender)
                R.id.rbFemale -> editor.putString("gender", femaleGender)
            }
            editor.apply()
            startActivity(Intent(this@FirstActivity, SecondActivity::class.java))
        }
    }

    private fun getDate() {
        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, R.style.DatePickerTheme, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            Toast.makeText(this@FirstActivity, "Your time is selected", Toast.LENGTH_SHORT).show()
            val editor = pref!!.edit()
            editor.putInt("day", dayOfMonth)
            editor.putInt("month", monthOfYear)
            editor.putInt("year", year)
            editor.apply()
        }, mYear, mMonth, mDay)

        datePickerDialog.show()

    }

    private fun showNullInputMessage() {
        edtUsername!!.error = "Username is required!"
        edtUsername!!.requestFocus()
        edtPassword?.error = "Password is required!"
    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
