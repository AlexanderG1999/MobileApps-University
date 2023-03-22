package com.example.ultdietf

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.ultdietf.db.DbDiet
import com.example.ultdietf.db.DbUser

class alternativas_dietas_activity : Activity() {
    private var choose_yout_diet: TextView? = null
    private var btn_diet1: View? = null
    private var btn_diet2: View? = null
    private var btn_diet3: View? = null
    private var option_1: TextView? = null
    private var option_3: TextView? = null
    private var option_2: TextView? = null
    private var left_5: ImageView? = null
    private var diet: ImageView? = null
    private var rectangle_19: View? = null
    private var skip: TextView? = null
    private var rectangle_18: View? = null
    private var choose: TextView? = null
    private var salad: ImageView? = null
    private var meat: ImageView? = null
    private var fish: ImageView? = null

    //Skip Button
    private var btn_skip1: RelativeLayout? = null
    // Choose Button
    private var btn_choose2: RelativeLayout? = null
    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alternativas_dietas)
        choose_yout_diet = findViewById<View>(R.id.choose_yout_diet) as TextView
        btn_diet1 = findViewById(R.id.btn_diet1) as View
        btn_diet2 = findViewById(R.id.btn_diet2) as View
        btn_diet3 = findViewById(R.id.btn_diet3) as View
        option_1 = findViewById<View>(R.id.option_1) as TextView
        option_3 = findViewById<View>(R.id.option_3) as TextView
        option_2 = findViewById<View>(R.id.option_2) as TextView
        left_5 = findViewById<View>(R.id.left_5) as ImageView
        diet = findViewById<View>(R.id.diet) as ImageView
        rectangle_19 = findViewById<View>(R.id.rectangle_19) as View
        skip = findViewById<View>(R.id.skip) as TextView
        rectangle_18 = findViewById<View>(R.id.rectangle_18) as View
        choose = findViewById<View>(R.id.choose) as TextView
        salad = findViewById<View>(R.id.salad) as ImageView
        meat = findViewById<View>(R.id.meat) as ImageView
        fish = findViewById<View>(R.id.fish) as ImageView

        // Get the list of diets depending the goal
        val diet = DbDiet(0,"")
        val dietsList = diet.selectDiets(this)
        option_1!!.text = dietsList[0].toString()
        option_2!!.text = dietsList[1].toString()
        option_3!!.text = dietsList[2].toString()

        // Choose a diet
        val focus_seleccionado = ContextCompat.getColor(this, R.color.seleccionado_color)
        val unfocus_seleccionado = ContextCompat.getColor(this, R.color.what_is_your_goal__color)

        //Elección y Detección de los tipos
        val opcion_diet1 = findViewById<View>(R.id.btn_diet1) as View
        opcion_diet1.setOnClickListener {
            DbUser.chooseDiet = 1
            btn_diet1!!.setBackgroundColor(focus_seleccionado)
            btn_diet2!!.setBackgroundColor(unfocus_seleccionado)
            btn_diet3!!.setBackgroundColor(unfocus_seleccionado)
        }

        val opcion_diet2 = findViewById<View>(R.id.btn_diet2) as View
        opcion_diet2.setOnClickListener {
            DbUser.chooseDiet = 2
            btn_diet1!!.setBackgroundColor(unfocus_seleccionado)
            btn_diet2!!.setBackgroundColor(focus_seleccionado)
            btn_diet3!!.setBackgroundColor(unfocus_seleccionado)
        }

        val opcion_diet3 = findViewById<View>(R.id.btn_diet3) as View
        opcion_diet3.setOnClickListener {
            DbUser.chooseDiet = 3
            btn_diet1!!.setBackgroundColor(unfocus_seleccionado)
            btn_diet2!!.setBackgroundColor(unfocus_seleccionado)
            btn_diet3!!.setBackgroundColor(focus_seleccionado)
        }

        // Skip to dashboard
        skip!!.setOnClickListener{
            DbUser.chooseDiet = 1
            // Save diet and update the last user (default)
            var userAux = DbUser(0,0,"","","","","","")
            var dietAux = DbDiet(0, "")
            val idLastUser = userAux.getTotalUsers(this)
            userAux = userAux.getUserById(this, idLastUser)
            userAux.setidDiet(dietAux.getRealIdDiet(DbUser.chooseGoal, DbUser.chooseDiet))
            val answerDiet = userAux.updateUser(this)
            goToActivity(dashboard_activity::class.java)
        }
        // Save Diet to dashboard revisar*
        choose!!.setOnClickListener{
            // Save diet and update the last user
            var userAux = DbUser(0,0,"","","","","","")
            var dietAux = DbDiet(0, "")
            val idLastUser = userAux.getTotalUsers(this)
            userAux = userAux.getUserById(this, idLastUser)
            userAux.setidDiet(dietAux.getRealIdDiet(DbUser.chooseGoal, DbUser.chooseDiet))
            val answerDiet = userAux.updateUser(this)

            if(answerDiet > 0){
                Toast.makeText(this, "DIET REGISTERED", Toast.LENGTH_LONG).show()
                goToActivity(dashboard_activity::class.java)
            }else{
                Toast.makeText(this, "ERROR REGISTERING THE DIET", Toast.LENGTH_LONG).show()
            }
        }
        //go back
        left_5?.setOnClickListener{
          //  goToActivity(configuraci_n_incial_2_activity::class.java)
        }
    }
    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}