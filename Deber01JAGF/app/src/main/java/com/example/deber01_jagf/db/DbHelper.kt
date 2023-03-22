package com.example.deber01_jagf.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "DBdeber01.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaAlbum =
            "CREATE TABLE t_album(" +
                    "id_album INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_album TEXT NOT NULL," +
                    "autor_album TEXT NOT NULL," +
                    "fechaPublicacion TEXT NOT NULL," +
                    "estadoFavorito TEXT NOT NULL);"

        val scriptSQLCrearTablaCancion =
            "CREATE TABLE t_cancion(" +
                    "id_cancion INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_cancion TEXT NOT NULL," +
                    "autor_cancion TEXT NOT NULL," +
                    "calificacion TEXT NOT NULL," +
                    "fechaPublicacion TEXT NOT NULL, " +
                    "IDalbum INTEGER NOT NULL," +
                    "FOREIGN KEY(IDalbum) REFERENCES t_album(id_album));"

        db?.execSQL(scriptSQLCrearTablaAlbum)
        db?.execSQL(scriptSQLCrearTablaCancion)
    }

    // Se ejecuta cuando la version cambia
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_cancion")
        db?.execSQL("DROP TABLE IF EXISTS t_album")
        onCreate(db)
    }
}