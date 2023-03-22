package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ultdietf.R
import com.example.ultdietf.db.DbUser

class login_activity : Activity() {
    private var _bg__login_ek2: View? = null
    private var welcome_back__: TextView? = null
    private var left_1: ImageView? = null
    private var waving_hand: ImageView? = null
    private var rectangle_2: View? = null
    private var email: TextView? = null
    private var email_1: ImageView? = null
    private var password: TextView? = null
    private var rectangle_3: View? = null
    private var password_1: ImageView? = null
    private var rectangle_4: View? = null
    private var login_ek3: TextView? = null
    private var forgot_password_: TextView? = null
    private var txt_email_l: EditText? = null
    private var txt_password_l: EditText? = null

    //Botón Ingreso
    private var btn_ingreso: RelativeLayout? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        _bg__login_ek2 = findViewById(R.id._bg__login_ek2) as View
        welcome_back__ = findViewById<View>(R.id.welcome_back__) as TextView
        left_1 = findViewById<View>(R.id.left_1) as ImageView
        waving_hand = findViewById<View>(R.id.waving_hand) as ImageView
        rectangle_2 = findViewById(R.id.rectangle_2) as View
        txt_email_l = findViewById<EditText>(R.id.txt_email_l) as EditText
        email_1 = findViewById<View>(R.id.email_1) as ImageView
        txt_password_l = findViewById<EditText>(R.id.txt_password_l) as EditText
        rectangle_3 = findViewById(R.id.rectangle_3) as View
        password_1 = findViewById<View>(R.id.password_1) as ImageView
        rectangle_4 = findViewById(R.id.rectangle_4) as View
        login_ek3 = findViewById<View>(R.id.login_ek3) as TextView
        forgot_password_ = findViewById<View>(R.id.forgot_password_) as TextView

        //boton ingreso
        btn_ingreso = findViewById<RelativeLayout>(R.id.btn_ingreso) as RelativeLayout
        cleanEditText()

        //Back a outcomming
        val btn_back = findViewById<View>(R.id.left_1) as ImageView
        btn_back.setOnClickListener {
            goToActivity(outcomming2_activity::class.java)
        }

        // Verify user and password
        val userAux = DbUser(0,0,"","","","","","")
        btn_ingreso!!.setOnClickListener {
            val answer = userAux.verifyUserAndPassword(this, txt_email_l!!.text.toString(), txt_password_l!!.text.toString())
            if(answer){
                Toast.makeText(this, "USER VERIFIED", Toast.LENGTH_LONG).show()
                // Set this value in my_goals_activity like (tv_tgWeight.text = DbUser.userAux.getTargetWeight())
                DbUser.userAux.settargetWeight(userAux.getUserByEmail(this, txt_email_l!!.text.toString()).gettargetWeight())
                DbUser.userAux.setname(userAux.getUserByEmail(this, txt_email_l!!.text.toString()).getname())

                cleanEditText()
                goToActivity(dashboard_activity::class.java)
            }else{
                Toast.makeText(this, "WRONG USER OR PASSWORD", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText(){
        txt_email_l!!.setText("")
        txt_password_l!!.setText("")
        txt_email_l!!.hint = "email"
        txt_password_l!!.hint = "password"
    }

    fun goToActivity(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}