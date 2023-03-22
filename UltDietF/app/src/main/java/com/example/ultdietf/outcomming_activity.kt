package com.example.ultdietf

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.ultdietf.db.DbDiet
import com.example.ultdietf.db.DbFood
import com.example.ultdietf.db.DbHelper
import com.example.ultdietf.db.DbUser

class outcomming_activity : Activity() {
    private var _bg__outcomming_ek2: View? = null
    private var logomovlies_1: ImageView? = null
    private var eiliv_aceron_nniwipdxbpg_unsplash_1: ImageView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outcomming)
        _bg__outcomming_ek2 = findViewById(R.id._bg__outcomming_ek2) as View
        logomovlies_1 = findViewById<View>(R.id.logomovlies_1) as ImageView
        eiliv_aceron_nniwipdxbpg_unsplash_1 =
            findViewById<View>(R.id.eiliv_aceron_nniwipdxbpg_unsplash_1) as ImageView


        //custom code goes here
        Handler().postDelayed({
            val intent = Intent(this@outcomming_activity, SIGUIENTE_ACTIVIDAD)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, TIEMPO_DEMOSTRACION.toLong())

        // Create DB
        val dbHelper: DbHelper = DbHelper(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        if (db != null) {
            //Toast.makeText(this, "DATABASE CREATED", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "ERROR WHILE CREATING DATABASE", Toast.LENGTH_LONG).show()
        }
        // End create DB

        /*// Insert Diet
        val diet1 = DbDiet(1, "Dieta Flexitariana")
        val diet2 = DbDiet(2, "Dieta Keto")
        val diet3 = DbDiet(3, "Dieta Mediterránea")
        val diet4 = DbDiet(4, "Dieta Hipercalórica 1")
        val diet5 = DbDiet(5, "Dieta Hipercalórica 2")
        val diet6 = DbDiet(6, "Dieta Hipercalórica 3")
        val diet7 = DbDiet(7, "Dieta Proteica")
        val diet8 = DbDiet(8, "Dieta Vegetariana")
        val diet9 = DbDiet(9, "Dieta Normal")
        diet1.inserDiet(this)
        diet2.inserDiet(this)
        diet3.inserDiet(this)
        diet4.inserDiet(this)
        diet5.inserDiet(this)
        diet6.inserDiet(this)
        diet7.inserDiet(this)
        diet8.inserDiet(this)
        diet9.inserDiet(this)
        // End insert diet

        // Insert User
        val user1 = DbUser(1, 1,"alexanderguillin1999@gmail.com", "Alexander Guillin", "admin", "1.70", "60", "55")
        val user2 = DbUser(2, 1,"carlos.estrada@epn.edu.ec", "Carlos Estrada", "admin", "1.72", "60", "55")
        user1.insertUser(this)
        user2.insertUser(this)
        // End insert user

        // Insert Food
        val food1 = DbFood(1,1, "Huevos revueltos + batido de guineo con leche", "Desayuno")
        val food2 = DbFood(2,1, "Crema de verduras + pan de ajo", "Cena")
        val food3 = DbFood(3,2, "Huevos revueltos con albahaca y mantequilla + tasa de café", "Desayuno")
        val food4 = DbFood(4,2, "Pollo frito con mantequilla y brócoli", "Cena")
        val food5 = DbFood(5,3, "Ensalada de frutas + nueces + yogurt", "Desayuno")
        val food6 = DbFood(6,3, "Pescado a la plancha con verduras + jugo de naranja", "Cena")
        val food7 = DbFood(7,4, "Batido de fresas + 3 huevos duros + pan con queso", "Desayuno")
        val food8 = DbFood(8,4, "Pechuga de pollo en crema de champiñones + arroz", "Cena")
        val food9 = DbFood(9,5, "Batido de guineo con avena + 3 huevos duros + pan con queso", "Desayuno")
        val food10 = DbFood(10,5, "Pechuga de pollo rellena de queso", "Cena")
        val food11 = DbFood(11,6, "Batido de guineo con avena + 3 tostadas con guacamole", "Desayuno")
        val food12 = DbFood(12,6, "Pizza de carnes", "Cena")
        val food13 = DbFood(13,7, "Yogurt desnatado natural + ensalada de frutas con nueces", "Desayuno")
        val food14 = DbFood(14,7, "Salmón ahumado", "Cena")
        val food15 = DbFood(15,8, "Champiñones asados con aguacate", "Desayuno")
        val food16 = DbFood(16,8, "Ensalada de aguacate", "Cena")
        val food17 = DbFood(17,9, "Omelette de queso con verduras", "Desayuno")
        val food18 = DbFood(18,9, "Lasaña de carne", "Cena")
        food1.insertFood(this)
        food2.insertFood(this)
        food3.insertFood(this)
        food4.insertFood(this)
        food5.insertFood(this)
        food6.insertFood(this)
        food7.insertFood(this)
        food8.insertFood(this)
        food9.insertFood(this)
        food10.insertFood(this)
        food11.insertFood(this)
        food12.insertFood(this)
        food13.insertFood(this)
        food14.insertFood(this)
        food15.insertFood(this)
        food16.insertFood(this)
        food17.insertFood(this)
        food18.insertFood(this)
        // End insert food*/
    }

    companion object {
        private const val TIEMPO_DEMOSTRACION = 3000 // 2 segundos
        private val SIGUIENTE_ACTIVIDAD: Class<*> = outcomming2_activity::class.java
    }
}