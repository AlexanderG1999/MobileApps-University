package com.example.ultdietf.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbFood(
    private var idFood: Int,
    private var idDiet: Int,
    private var description: String,
    private var schedule: String
) {
    init {
        idFood
        idDiet
        description
        schedule
    }

    fun setidFood(idFood: Int) {
        this.idFood = idFood
    }

    fun setidDiet(idDiet: Int) {
        this.idDiet = idDiet
    }

    fun setdescription(description: String) {
        this.description = description
    }

    fun setschedule(schedule: String) {
        this.schedule = schedule
    }

    fun getidFood(): Int {
        return idFood
    }

    fun getidDiet(): Int {
        return idDiet
    }

    fun getdescription(): String {
        return description
    }

    fun getschedule(): String {
        return schedule
    }

    fun insertFood(context: Context): Long {
        val dbHelper: DbHelper = DbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("id_dieta", this.idDiet)
        values.put("descripcion_comida", this.description)
        values.put("horario_comida", this.schedule)

        return db.insert("t_comida", null, values)
    }

    fun selectFoods(context: Context): ArrayList<DbFood> {
        var list = ArrayList<DbFood>()
        val dbHelper: DbHelper = DbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var food: DbFood
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT id_dieta, descripcion_comida, horario_comida FROM t_comida", null)
        var dietAux = DbDiet(0, "")
        var idRealDiet = dietAux.getRealIdDiet(DbUser.chooseGoal, DbUser.chooseDiet)

        if (cursor.moveToFirst()) {
            do {
                food = DbFood(0, cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2))
                if(cursor.getString(0).toInt() == idRealDiet){
                    list.add(food)
                }
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun toString(): String {
        return this.description
    }
}