package com.example.exameniib_jagf.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.google.firebase.database.*

class DbCancion(
    //Atributos
    private var idCancion: String,
    private var nombreCancion: String,
    private var autorCancion: String,
    private var calificacion: String,
    private var fechaPublicacion: String,
    private var fkAlbum: String,
    private val context: Context?
) {
    init {
        idCancion
        nombreCancion
        autorCancion
        calificacion
        fechaPublicacion
        fkAlbum
        context
    }

    fun setidCancion(idCancion: String) {
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

    fun setidAlbum(idAlbum: String) {
        this.fkAlbum = idAlbum
    }

    fun getidCancion(): String? {
        return idCancion
    }

    fun getidAlbum(): String {
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

    fun insertCancion(databaseReference: DatabaseReference): Long {
        databaseReference.child("Cancion").child(this.idCancion).setValue(this)
        return 1
    }

    fun showCanciones(id: String): ArrayList<DbCancion> {
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.getReference("Cancion")

        var lista = ArrayList<DbCancion>()

        var messageListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                lista.clear()
                dataSnapshot.children.forEach { child: DataSnapshot ->
                    val cancion: DbCancion? =
                        DbCancion(
                            child.child("idCancion").getValue() as String,
                            child.child("nombreCancion").getValue() as String,
                            child.child("autorCancion").getValue() as String,
                            child.child("calificacion").getValue() as String,
                            child.child("fechaPublicacion").getValue() as String,
                            child.child("idAlbum").getValue() as String,
                            null
                        )

                    cancion?.let {
                        if (id.equals(it.getidAlbum())) {
                            lista.add(it)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.addValueEventListener(messageListener)

        return lista
    }

    fun getNameAlbum(id: String): String {
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.getReference("Album")
        var nombreAlbum = ""

        var messageListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { child: DataSnapshot ->
                    val album: DbAlbum? =
                        DbAlbum(
                            child.child("idAlbum").getValue() as String,
                            child.child("nombreAlbum").getValue() as String,
                            child.child("autorAlbum").getValue() as String,
                            child.child("fechaPublicacion").getValue() as String,
                            child.child("estadoFavorito").getValue() as String,
                            null
                        )
                    album?.let {
                        if (id.equals(it.getidAlbum())) {
                            nombreAlbum = it.getnombreAlbum()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.addValueEventListener(messageListener)

        return nombreAlbum
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