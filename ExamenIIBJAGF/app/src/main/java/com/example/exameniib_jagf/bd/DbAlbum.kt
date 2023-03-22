package com.example.exameniib_jagf.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.exameniib_jagf.Album
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DbAlbum(
    // Atributos
    private var idAlbum: String,
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

    fun setidAlbum(idAlbum: String) {
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

    fun getidAlbum(): String? {
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

    fun insertAlbum(databaseReference: DatabaseReference): Long {
        databaseReference.child("Album").child(this.idAlbum).setValue(this)
        return 1
    }

    fun showAlbumes(): ArrayList<DbAlbum> {
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.getReference("Album")

        var lista = ArrayList<DbAlbum>()

        var messageListener: ValueEventListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                lista.clear()
                dataSnapshot.children.forEach {child : DataSnapshot ->
                    val album: DbAlbum? =
                        DbAlbum(child.child("idAlbum").getValue() as String,
                        child.child("nombreAlbum").getValue() as String,
                        child.child("autorAlbum").getValue() as String,
                        child.child("fechaPublicacion").getValue() as String,
                        child.child("estadoFavorito").getValue() as String,
                        null)

                    album?.let { lista.add(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.addValueEventListener(messageListener)

        return lista
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