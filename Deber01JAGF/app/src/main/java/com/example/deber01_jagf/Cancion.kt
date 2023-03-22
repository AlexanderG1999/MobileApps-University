package com.example.deber01_jagf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.deber01_jagf.db.DbCancion

class Cancion : AppCompatActivity() {
    var idItemSeleccionado = 0

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
                null,
                nombre.text.toString(),
                autor.text.toString(),
                calificacion.text.toString(),
                fecha.text.toString(),
                idAlbum.text.toString().toInt(),
                this
            )
            val resultado = cancion.insertCancion()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
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
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_canc -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar_canc -> {
                //abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}