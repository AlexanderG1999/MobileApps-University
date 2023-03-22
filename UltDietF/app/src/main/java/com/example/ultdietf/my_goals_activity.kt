package com.example.ultdietf

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.ultdietf.db.DbUser

class my_goals_activity : Activity() {
    private var my_target_weight: TextView? = null
    private var my_target_weight_ek1: TextView? = null
    private var rectangle_22: View? = null
    private var scale: ImageView? = null
    private var _65_kg: TextView? = null
    private var rectangle_5: View? = null
    private var lose_weight: TextView? = null
    private var lose_weight_ek1: ImageView? = null
    private var left_6: ImageView? = null
    private var rectangle_23: View? = null
    private var save: TextView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_goals)
        my_target_weight = findViewById<View>(R.id.my_target_weight) as TextView
        my_target_weight_ek1 = findViewById<View>(R.id.my_target_weight_ek1) as TextView
        rectangle_22 = findViewById<View>(R.id.rectangle_22) as View
        scale = findViewById<View>(R.id.scale) as ImageView
        _65_kg = findViewById<View>(R.id._65_kg) as TextView
        rectangle_5 = findViewById(R.id.rectangle_5) as View
        lose_weight = findViewById<View>(R.id.lose_weight) as TextView
        lose_weight_ek1 = findViewById<View>(R.id.lose_weight_ek1) as ImageView
        left_6 = findViewById<View>(R.id.left_6) as ImageView
        rectangle_23 = findViewById<View>(R.id.rectangle_23) as View
        save = findViewById<View>(R.id.save) as TextView

        // Set target weight
        _65_kg!!.text = DbUser.userAux.gettargetWeight() + " KG"
    }
}