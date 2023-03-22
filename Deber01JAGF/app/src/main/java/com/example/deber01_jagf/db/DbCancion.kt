package com.example.deber01_jagf.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbCancion(
    //Atributos
    private var idCancion: Int?,
    private var nombreCancion: String,
    private var autorCancion: String,
    private var calificacion: String,
    private var fechaPublicacion: String,
    private var fkAlbum: Int,
    private val context: Context?
) {
    init {
        nombreCancion
        autorCancion
        calificacion
        fechaPublicacion
        fkAlbum
        context
    }

    fun setidCancion(idCancion: Int) {
        this.idCancion = idCancion
    }

    fun setnombreCancion(nombreCancion: String) {
        this.nombreCancion = nombreCancion
    }

    fun setautorCancion(autorCancion: String) {
        this.autorCancion = autorCancion
    }

    fun setcalificacion(calificacion: String) {
        this.calificacion = calificacion
    }

    fun setfechaPublicacion(fechaPublicacion: String) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun setidAlbum(idAlbum: Int) {
        this.fkAlbum = idAlbum
    }

    fun getidCancion(): Int? {
        return idCancion
    }

    fun getidAlbum(): Int {
        return fkAlbum
    }

    fun getnombreCancion(): String {
        return nombreCancion
    }

    fun getautorCancion(): String {
        return autorCancion
    }

    fun getcalificacion(): String {
        return calificacion
    }

    fun getfechaPublicacion(): String {
        return fechaPublicacion
    }

    fun insertCancion(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_cancion", this.nombreCancion)
        values.put("autor_cancion", this.autorCancion)
        values.put("calificacion", this.calificacion)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDalbum", this.fkAlbum)

        return db.insert("t_cancion", null, values)
    }

    fun showCanciones(id: Int): ArrayList<DbCancion> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var listaCanciones = ArrayList<DbCancion>()
        var cancion: DbCancion
        var cursorCancion: Cursor? = null

        // Ver si el id: Int es diferente de null
        cursorCancion = db.rawQuery("SELECT * FROM t_cancion WHERE IDalbum=${id+1}", null)

        if (cursorCancion.moveToFirst()) {
            do {
                cancion = DbCancion(null, "", "", "", "", 0, null)

                cancion.setidCancion(cursorCancion.getString(0).toInt())
                cancion.setnombreCancion(cursorCancion.getString(1))
                cancion.setautorCancion(cursorCancion.getString(2))
                cancion.setcalificacion(cursorCancion.getString(3))
                cancion.setfechaPublicacion(cursorCancion.getString(4))
                cancion.setidAlbum(cursorCancion.getString(5).toInt())
                listaCanciones.add(cancion)
            } while (cursorCancion.moveToNext())
        }

        cursorCancion.close()
        return listaCanciones
    }

    fun getCancionById(id: Int): DbCancion{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var cancion = DbCancion(null, "", "", "", "",0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_cancion WHERE id_cancion = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                cancion.setidCancion(cursor.getString(0).toInt())
                cancion.setnombreCancion(cursor.getString(1))
                cancion.setautorCancion(cursor.getString(2))
                cancion.setcalificacion(cursor.getString(3))
                cancion.setfechaPublicacion(cursor.getString(4))
                cancion.setidAlbum(cursor.getString(5).toInt())
            } while (cursor.moveToNext())
        }

        cursor.close()
        return cancion
    }

    fun updateCancion(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_cancion", this.nombreCancion)
        values.put("autor_cancion", this.autorCancion)
        values.put("calificacion", this.calificacion)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDalbum", this.fkAlbum)

        return db.update("t_cancion", values, "id_cancion="+this.idCancion, null)
    }

    fun deleteCancion(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_cancion", "id_cancion="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. canción: ${idCancion}\n" +
                    "Canción: ${nombreCancion}\n" +
                    "Autor: ${autorCancion}\n" +
                    "Calificación: ${calificacion} \n" +
                    "Fecha de publicación: ${fechaPublicacion}\n" +
                    "Núm. álbum: ${fkAlbum}"

        return salida
    }
}