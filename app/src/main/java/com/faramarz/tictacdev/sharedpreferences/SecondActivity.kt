package com.faramarz.tictacdev.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        changeStatusBarColor()
        pref = getSharedPreferences("user_details", Context.MODE_PRIVATE)
        txtName!!.text = with(pref) { this!!.getString("username", null) }
        txtPass!!.text = with(pref) { this!!.getString("password", null) }
        txtGen!!.text = with(pref) { this!!.getString("gender", null) }
        txtDate!!.text = with(pref) { this!!.getInt("day", 0).toString() } + "-" +
                with(pref) { this!!.getInt("month", 0) } + "-" +
                with(pref) { this!!.getInt("year", 0) }

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
