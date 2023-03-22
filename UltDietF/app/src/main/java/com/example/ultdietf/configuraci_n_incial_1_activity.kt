package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.ultdietf.R
import com.example.ultdietf.db.DbUser

class configuraci_n_incial_1_activity : Activity() {
    private var what_is_your_goal_: TextView? = null
    private var left_2: ImageView? = null
    private var award: ImageView? = null
    private var rectangle_7: View? = null
    private var stay_healthy: TextView? = null
    private var healthy: ImageView? = null
    private var rectangle_5: View? = null
    private var lose_weight: TextView? = null
    private var lose_weight_ek1: ImageView? = null
    private var rectangle_6: View? = null
    private var gain_weight: TextView? = null
    private var barbell: ImageView? = null
    private var rectangle_8: View? = null
    private var siguiente: TextView? = null
    private var is_pressed = false

    //Boton siguiente 1
    private var btn_siguiente1: RelativeLayout? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuraci_n_incial_1)
        what_is_your_goal_ = findViewById<View>(R.id.what_is_your_goal_) as TextView
        left_2 = findViewById<View>(R.id.left_2) as ImageView
        award = findViewById<View>(R.id.award) as ImageView
        rectangle_7 = findViewById(R.id.rectangle_7) as View
        stay_healthy = findViewById<View>(R.id.stay_healthy) as TextView
        healthy = findViewById<View>(R.id.healthy) as ImageView
        rectangle_5 = findViewById(R.id.rectangle_5) as View
        lose_weight = findViewById<View>(R.id.lose_weight) as TextView
        lose_weight_ek1 = findViewById<View>(R.id.lose_weight_ek1) as ImageView
        rectangle_6 = findViewById(R.id.rectangle_6) as View
        gain_weight = findViewById<View>(R.id.gain_weight) as TextView
        barbell = findViewById<View>(R.id.barbell) as ImageView
        rectangle_8 = findViewById(R.id.rectangle_8) as View
        siguiente = findViewById<View>(R.id.siguiente) as TextView

        //btn siguiente
        btn_siguiente1 = findViewById<RelativeLayout>(R.id.btn_siguiente1) as RelativeLayout

        btn_siguiente1!!.setOnClickListener {
            goToActivity(configuraci_n_incial_2_activity::class.java)
        }

        // get back to start
        left_2!!.setOnClickListener{
            goToActivity(outcomming2_activity::class.java)
        }

        // Get the goal of the user with the companion object

        //custom code goes here
        val focus_seleccionado = ContextCompat.getColor(this, R.color.seleccionado_color)
        val unfocus_seleccionado = ContextCompat.getColor(this, R.color.what_is_your_goal__color)
        //Listener Regresar a start
        val btn_back = findViewById<View>(R.id.left_2) as ImageView
        btn_back.setOnClickListener {
            goToActivity(outcomming2_activity::class.java)
        }

        //Elección y Detección de los tipos
        val opcion_loss = findViewById<View>(R.id.btn_lose_w) as RelativeLayout
        opcion_loss.setOnClickListener {
            DbUser.chooseGoal = 1
            rectangle_5!!.setBackgroundColor(focus_seleccionado)
            rectangle_6!!.setBackgroundColor(unfocus_seleccionado)
            rectangle_7!!.setBackgroundColor(unfocus_seleccionado)
        }

        val opcion_gain = findViewById<View>(R.id.btn_gain_w) as RelativeLayout
        opcion_gain.setOnClickListener {
            DbUser.chooseGoal = 2
            rectangle_5!!.setBackgroundColor(unfocus_seleccionado)
            rectangle_6!!.setBackgroundColor(focus_seleccionado)
            rectangle_7!!.setBackgroundColor(unfocus_seleccionado)
        }

        val opcion_stay = findViewById<View>(R.id.btn_stay_g) as RelativeLayout
        opcion_stay.setOnClickListener {
            DbUser.chooseGoal = 3
            rectangle_5!!.setBackgroundColor(unfocus_seleccionado)
            rectangle_6!!.setBackgroundColor(unfocus_seleccionado)
            rectangle_7!!.setBackgroundColor(focus_seleccionado)
        }
    }

    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}