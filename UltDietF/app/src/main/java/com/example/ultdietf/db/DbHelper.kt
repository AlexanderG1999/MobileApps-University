package com.example.ultdietf.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "ProjectIIBMov.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCreateTableUser =
            "CREATE TABLE t_usuario(" +
                    "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email_usuario TEXT NOT NULL," +
                    "nombre_usuario TEXT NOT NULL," +
                    "password_usuario TEXT NOT NULL," +
                    "altura_usuario TEXT NOT NULL," +
                    "pesoact_usuario TEXT NOT NULL," +
                    "pesoobj_usuario TEXT NOT NULL," +
                    "id_dieta INTEGER NOT NULL," +
                    "FOREIGN KEY(id_dieta) REFERENCES t_dieta(id_dieta));"

        val scriptSQLCreateTableDiet =
            "CREATE TABLE t_dieta(" +
                    "id_dieta INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tipo_dieta TEXT NOT NULL);"

        val scriptSQLCreateTableFood =
            "CREATE TABLE t_comida(" +
                    "id_comida INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_dieta INTEGER NOT NULL," +
                    "descripcion_comida TEXT NOT NULL," +
                    "horario_comida TEXT NOT NULL," +
                    "FOREIGN KEY(id_dieta) REFERENCES t_dieta(id_dieta));"

        db?.execSQL(scriptSQLCreateTableDiet)
        db?.execSQL(scriptSQLCreateTableUser)
        db?.execSQL(scriptSQLCreateTableFood)
    }

    // Runs when version changes
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_dieta")
        db?.execSQL("DROP TABLE IF EXISTS t_usuario")
        db?.execSQL("DROP TABLE IF EXISTS t_comida")
        onCreate(db)
    }
}