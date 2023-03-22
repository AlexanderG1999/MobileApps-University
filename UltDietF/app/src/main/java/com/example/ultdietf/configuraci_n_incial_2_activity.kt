package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ultdietf.db.DbUser

class configuraci_n_incial_2_activity : Activity() {
    private var _bg__configuraci_n_incial_2_ek2: View? = null
    private var left_3: ImageView? = null
    private var what_is_your_height: TextView? = null
    private var what_is_your_weight: TextView? = null
    private var what_is_your_target_weight: TextView? = null
    private var rectangle_9: ImageView? = null
    private var rectangle_10: View? = null
    private var rectangle_11: ImageView? = null
    private var txt_altura: EditText? = null
    private var rectangle_12: View? = null
    private var siguiente: TextView? = null
    private var txt_peso_a: EditText? = null
    private var txt_peso_m: EditText? = null
    //btn siguiente config2
    private var btn_siguiente2: RelativeLayout? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuraci_n_incial_2)
        _bg__configuraci_n_incial_2_ek2 =
            findViewById<View>(R.id._bg__configuraci_n_incial_2_ek2) as View
        //Botón back
        left_3 = findViewById<View>(R.id.left_3) as ImageView

        what_is_your_height = findViewById<View>(R.id.what_is_your_height) as TextView
        what_is_your_weight = findViewById<View>(R.id.what_is_your_weight) as TextView
        what_is_your_target_weight = findViewById<View>(R.id.what_is_your_target_weight) as TextView
        rectangle_9 = findViewById<View>(R.id.rectangle_9) as ImageView
        rectangle_10 = findViewById<View>(R.id.rectangle_10) as View
        rectangle_11 = findViewById<View>(R.id.rectangle_11) as ImageView
        txt_altura = findViewById<View>(R.id.txt_altura) as EditText
        rectangle_12 = findViewById<View>(R.id.rectangle_12) as View
        siguiente = findViewById<View>(R.id.siguiente) as TextView
        txt_peso_a = findViewById<View>(R.id.txt_peso_a) as EditText
        txt_peso_m = findViewById<View>(R.id.txt_peso_m) as EditText

        //botón siguiente
        btn_siguiente2 = findViewById<RelativeLayout>(R.id.btn_siguiente2) as RelativeLayout


        //botón back a configuración inicial 1
        left_3!!.setOnClickListener{
            goToActivity(configuraci_n_incial_1_activity::class.java)
        }
        cleanEditText()

        // Get height, actual weight and target weight
        btn_siguiente2!!.setOnClickListener {
            DbUser.userAux.setheight(txt_altura!!.text.toString())
            DbUser.userAux.setactualWeight(txt_peso_a!!.text.toString())
            DbUser.userAux.settargetWeight(txt_peso_m!!.text.toString())

            // Clean TextView
            cleanEditText()
            // Go to registro_activity
            goToActivity(registro_activity::class.java)
        }
    }

    fun cleanEditText(){
        txt_altura!!.setText("")
        txt_peso_a!!.setText("")
        txt_peso_m!!.setText("")
    }

    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}