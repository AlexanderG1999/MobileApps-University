package com.example.deber01_jagf.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbAlbum(
    // Atributos
    private var idAlbum: Int?,
    private var nombreAlbum: String,
    private var autorAlbum: String,
    private var fechaPublicacion: String,
    private var estadoFavorito: String,
    private val context: Context?
) {
    init {
        idAlbum
        nombreAlbum
        autorAlbum
        fechaPublicacion
        estadoFavorito
        context
    }

    fun setidAlbum(idAlbum: Int) {
        this.idAlbum = idAlbum
    }

    fun setnombreAlbum(nombreAlbum: String) {
        this.nombreAlbum = nombreAlbum
    }

    fun setautorAlbum(autorAlbum: String) {
        this.autorAlbum = autorAlbum
    }

    fun setfechaPublicacion(fechaPublicacion: String) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun setestadoFavorito(estadoFav: String) {
        this.estadoFavorito = estadoFav
    }

    fun getidAlbum(): Int? {
        return idAlbum
    }

    fun getnombreAlbum(): String {
        return nombreAlbum
    }

    fun getautorAlbum(): String {
        return autorAlbum
    }

    fun getfechaPublicacion(): String {
        return fechaPublicacion
    }

    fun getestadoFavorito(): String {
        return estadoFavorito
    }

    fun insertAlbum(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_album", this.nombreAlbum)
        values.put("autor_album", this.autorAlbum)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("estadoFavorito", this.estadoFavorito)

        return db.insert("t_album", null, values)
    }

    fun showAlbumes(): ArrayList<DbAlbum> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<DbAlbum>()
        var album: DbAlbum
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_album", null)

        if (cursor.moveToFirst()) {
            do {
                album = DbAlbum(null, "", "", "", "", null)

                album.setidAlbum(cursor.getString(0).toInt())
                album.setnombreAlbum(cursor.getString(1))
                album.setautorAlbum(cursor.getString(2))
                album.setfechaPublicacion(cursor.getString(3))
                album.setestadoFavorito(cursor.getString(4))
                lista.add(album)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun getAlbumById(id: Int): DbAlbum{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var album = DbAlbum(null, "", "", "", "", this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_album WHERE id_album = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                album.setidAlbum(cursor.getString(0).toInt())
                album.setnombreAlbum(cursor.getString(1))
                album.setautorAlbum(cursor.getString(2))
                album.setfechaPublicacion(cursor.getString(3))
                album.setestadoFavorito(cursor.getString(4))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return album
    }

    fun updateAlbum(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_album", this.nombreAlbum)
        values.put("autor_album", this.autorAlbum)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("estadoFavorito", this.estadoFavorito)

        return db.update("t_album", values, "id_album="+this.idAlbum, null)
    }

    fun deleteAlbum(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_album", "id_album="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. álbum: ${idAlbum}\n" +
                    "Álbum: ${nombreAlbum}\n" +
                    "Autor: ${autorAlbum}\n" +
                    "Fecha de publicación: ${fechaPublicacion}\n" +
                    "Estado Favorito: ${estadoFavorito}"

        return salida
    }
}