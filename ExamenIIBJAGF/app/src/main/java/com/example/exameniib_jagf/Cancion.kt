package com.example.exameniib_jagf

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.exameniib_jagf.bd.DbAlbum
import com.example.exameniib_jagf.bd.DbCancion
import java.util.*

class Cancion : AppCompatActivity() {
    var idItemSeleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion)

        val nombre = findViewById<EditText>(R.id.editText_cancion)
        nombre.requestFocus()
        val autor = findViewById<EditText>(R.id.editText_autor)
        val calificacion = findViewById<EditText>(R.id.editText_calificacion)
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        val idAlbum = findViewById<EditText>(R.id.editText_idalbum)

        val btnInsertar = findViewById<Button>(R.id.btn_insert)
        btnInsertar.setOnClickListener {
            val cancion: DbCancion = DbCancion(
                UUID.randomUUID().toString(),
                nombre.text.toString(),
                autor.text.toString(),
                calificacion.text.toString(),
                fecha.text.toString(),
                Album.idAlbumSeleccionado,
                this
            )
            val resultado = cancion.insertCancion(MainActivity.databaseReference)

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                irActividad(verCanciones::class.java)
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_cancion)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.editText_autor)
        autor.setText("")
        val calificacion = findViewById<EditText>(R.id.editText_calificacion)
        calificacion.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        fecha.setText("")
        val idAlbum = findViewById<EditText>(R.id.editText_idalbum)
        idAlbum.setText("")
        nombre.requestFocus()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menucancion, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
    }

    fun getCancion(){
        var cancion = DbCancion("", "", "", "", "", "",this)
        val lv_cancion = findViewById<ListView>(R.id.listView_cancion)
        lv_cancion.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cancion = parent?.getItemAtPosition(position) as DbCancion
                idItemSeleccionado = cancion.getidCancion().toString()
            }

        })
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}

