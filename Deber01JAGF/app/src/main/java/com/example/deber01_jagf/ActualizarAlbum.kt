package com.example.deber01_jagf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.deber01_jagf.db.DbAlbum

class ActualizarAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_album)

        val idAlbum = Album.idAlbumSeleccionado
        var album = DbAlbum(null, "", "", "", "", this)
        album = album.getAlbumById(idAlbum)

        var id = findViewById<EditText>(R.id.et_upd_idAlb)
        id.setText(album.getidAlbum().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombAlb)
        nombre.setText(album.getnombreAlbum())
        var autor = findViewById<EditText>(R.id.et_upd_autorAlb)
        autor.setText(album.getautorAlbum())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaAlb)
        fecha.setText(album.getfechaPublicacion())
        var estado = findViewById<EditText>(R.id.et_upd_estadoAlb)
        estado.setText(album.getestadoFavorito())

        val btn_actualizarAlbum = findViewById<Button>(R.id.btn_upd_Album)
        btn_actualizarAlbum.setOnClickListener {
            // Album actualizado
            album.setnombreAlbum(nombre.text.toString())
            album.setautorAlbum(autor.text.toString())
            album.setfechaPublicacion(fecha.text.toString())
            album.setestadoFavorito(estado.text.toString())
            val resultado = album.updateAlbum()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val id = findViewById<EditText>(R.id.et_upd_idAlb)
        id.setText("")
        val nombre = findViewById<EditText>(R.id.et_upd_nombAlb)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.et_upd_autorAlb)
        autor.setText("")
        val fecha = findViewById<EditText>(R.id.et_upd_fechaAlb)
        fecha.setText("")
        val estado = findViewById<EditText>(R.id.et_upd_estadoAlb)
        estado.setText("")
        id.requestFocus()
    }

}