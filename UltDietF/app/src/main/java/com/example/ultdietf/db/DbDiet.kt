package com.example.ultdietf.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbDiet(
    private var idDiet: Int,
    private var typeDiet: String
) {
    init {
        idDiet
        typeDiet
    }

    fun setidDiet(idDiet: Int) {
        this.idDiet = idDiet
    }

    fun settypeDiet(typeDiet: String) {
        this.typeDiet = typeDiet
    }

    fun getidDiet(): Int {
        return idDiet
    }

    fun gettypeDiet(): String {
        return typeDiet
    }

    fun inserDiet(context: Context): Long {
        val dbHelper: DbHelper = DbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("tipo_dieta", this.typeDiet)

        return db.insert("t_dieta", null, values)
    }

    fun selectDiets(context: Context): ArrayList<DbDiet> {
        var list = ArrayList<DbDiet>()
        val dbHelper: DbHelper = DbHelper(context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var diet: DbDiet
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_dieta", null)

        if (cursor.moveToFirst()) {
            do {
                diet = DbDiet(cursor.getString(0).toInt(), cursor.getString(1))
                when (DbUser.chooseGoal) {
                    (1) -> {
                        if(cursor.getString(0).toInt() in 1..3){
                            list.add(diet)
                        }
                    }
                    (2) -> {
                        if(cursor.getString(0).toInt() in 4..6){
                            list.add(diet)
                        }
                    }
                    (3) -> {
                        if(cursor.getString(0).toInt() in 7..9){
                            list.add(diet)
                        }
                    }
                }
            } while (cursor.moveToNext())
        }
        return list
    }

    fun getRealIdDiet(goal: Int, idDiet: Int): Int{
        var realIdDiet = 0
        when(goal){
            (1) -> {
                realIdDiet = idDiet
            }
            (2) -> {
                realIdDiet = idDiet + 3
            }
            (3) -> {
                realIdDiet = idDiet + 6
            }
        }
        return  realIdDiet
    }

    override fun toString(): String {
        return this.typeDiet
    }
}