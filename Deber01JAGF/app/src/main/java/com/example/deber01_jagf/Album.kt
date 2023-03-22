package com.example.deber01_jagf

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.deber01_jagf.db.DbAlbum

class Album : AppCompatActivity() {
    companion object{
        var idAlbumSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        showListViewAlbum()

        val nombre = findViewById<EditText>(R.id.editText_nombreAlb)
        nombre.requestFocus()
        val autor = findViewById<EditText>(R.id.editText_autorAlb)
        val fecha = findViewById<EditText>(R.id.editText_fechaAlb)
        val estadoFav = findViewById<EditText>(R.id.editText_estadoAlb)

        val btnInsertar = findViewById<Button>(R.id.btn_insertarAlb)
        btnInsertar.setOnClickListener {
            val album = DbAlbum(
                null,
                nombre.text.toString(),
                autor.text.toString(),
                fecha.text.toString(),
                estadoFav.text.toString(),
                this
            )
            val resultado = album.insertAlbum()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewAlbum()
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_nombreAlb)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.editText_autorAlb)
        autor.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fechaAlb)
        fecha.setText("")
        val estado = findViewById<EditText>(R.id.editText_estadoAlb)
        estado.setText("")
        nombre.requestFocus()
    }

    fun showListViewAlbum() {
        // ListView Canciones
        val album = DbAlbum(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.listview_album)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            album.showAlbumes()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menualbum, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idAlbumSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_canc -> {
                irActividad(ActualizarAlbum::class.java)
                return true
            }
            R.id.mi_eliminar_canc -> {
                abrirDialogo()
                return true
            }
            R.id.mi_vercanciones -> {
                irActividad(verCanciones::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este álbum?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val album = DbAlbum(null, "", "", "", "", this)
                val resultado = album.deleteAlbum(idAlbumSeleccionado)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewAlbum()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            }
        )
        builder.setNegativeButton(
            "NO",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}