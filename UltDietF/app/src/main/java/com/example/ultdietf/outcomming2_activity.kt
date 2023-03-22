package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ultdietf.R

class outcomming2_activity : Activity() {
    private var _bg__outcomming2_ek2: View? = null
    private var rectangle_1: View? = null
    private var sign_in: TextView? = null
    private var _3945191_1: ImageView? = null
    private var live_healthy_and_great_: TextView? = null
    private var ellipse_1: View? = null
    private var ellipse_2: View? = null
    private var start: TextView? = null
    private var or: TextView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outcomming2)
        _bg__outcomming2_ek2 = findViewById(R.id._bg__outcomming2_ek2) as View
        rectangle_1 = findViewById(R.id.rectangle_1) as View
        sign_in = findViewById<View>(R.id.sign_in) as TextView
        _3945191_1 = findViewById<View>(R.id._3945191_1) as ImageView
        live_healthy_and_great_ = findViewById<View>(R.id.live_healthy_and_great_) as TextView
        ellipse_1 = findViewById(R.id.ellipse_1) as View
        ellipse_2 = findViewById(R.id.ellipse_2) as View
        start = findViewById<View>(R.id.start) as TextView
        or = findViewById<View>(R.id.or) as TextView


        //custom code goes here
        //Listener Cambiar a configuracion 1
        val btn_start = findViewById<View>(R.id.btn_start) as RelativeLayout
        btn_start.setOnClickListener {
            goToActivity(configuraci_n_incial_1_activity::class.java)
        }
        //Listener a Login
        val btn_login = findViewById<View>(R.id.btn_login) as RelativeLayout
        btn_login.setOnClickListener {
            goToActivity(login_activity::class.java)
        }
    }

    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}