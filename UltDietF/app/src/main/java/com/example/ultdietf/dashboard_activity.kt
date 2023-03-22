package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ultdietf.db.DbFood

class dashboard_activity : Activity() {
    private var _bg__dashboard_ek2: View? = null
    private var image_1: ImageView? = null
    private var btn_foto_perfil: ImageView? = null
    private var have_a_good_day_: TextView? = null
    private var my_meals: TextView? = null
    private var star: ImageView? = null
    private var rectangle_5: View? = null
    private var rectangle_6: View? = null
    private var breakfast: TextView? = null
    private var dinner: TextView? = null
    private var breakfast_ek1: ImageView? = null
    private var meal: ImageView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        _bg__dashboard_ek2 = findViewById<View>(R.id._bg__dashboard_ek2) as View
        image_1 = findViewById<View>(R.id.image_1) as ImageView
        btn_foto_perfil = findViewById<ImageView>(R.id.btn_foto_perfil) as ImageView
        have_a_good_day_ = findViewById<View>(R.id.have_a_good_day_) as TextView
        my_meals = findViewById<View>(R.id.my_meals) as TextView
        star = findViewById<View>(R.id.star) as ImageView
        rectangle_5 = findViewById(R.id.rectangle_5) as View
        rectangle_6 = findViewById(R.id.rectangle_6) as View
        breakfast = findViewById<View>(R.id.breakfast) as TextView
        dinner = findViewById<View>(R.id.dinner) as TextView
        breakfast_ek1 = findViewById<View>(R.id.breakfast_ek1) as ImageView
        meal = findViewById<View>(R.id.meal) as ImageView

        // Get foods of the diets
        var foodAux = DbFood(0, 0, "","")
        var arrayListFood = foodAux.selectFoods(this)
        breakfast!!.setOnClickListener {
            Toast.makeText(this, arrayListFood[0].toString(), Toast.LENGTH_LONG).show()
        }
        dinner!!.setOnClickListener {
            Toast.makeText(this, arrayListFood[1].toString(), Toast.LENGTH_LONG).show()
        }
        btn_foto_perfil!!.setOnClickListener{
            goToActivity(perfil_activity::class.java)
        }
    }
    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}