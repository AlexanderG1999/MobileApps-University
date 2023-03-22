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
import com.example.deber01_jagf.db.DbCancion

class verCanciones : AppCompatActivity() {
    companion object {
        var idCancionSeleccionada = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_canciones)
        val idAlbum = Album.idAlbumSeleccionado
        var albumAux = DbAlbum(null, "", "", "", "", this)

        val textViewAutor = findViewById<TextView>(R.id.tv_albumVerCanciones)
        textViewAutor.text = "Álbum: "+ albumAux.getAlbumById(idAlbum).getnombreAlbum()

        val btnCrearCancion = findViewById<Button>(R.id.btn_crearCancion)
        btnCrearCancion.setOnClickListener {
            irActividad(Cancion::class.java)
        }
        showListView(idAlbum)
    }

    fun showListView(id: Int) {
        // ListView Canciones
        val objetoCancion = DbCancion(null, "", "", "", "", 0, this)
        val listViewCanciones = findViewById<ListView>(R.id.listView_cancion)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objetoCancion.showCanciones(id)
        )
        listViewCanciones.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewCanciones)
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
        idCancionSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_canc -> {
                irActividad(ActualizarCancion::class.java)
                return true
            }
            R.id.mi_eliminar_canc -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta canción?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val cancion = DbCancion(null, "", "", "", "", 0, this)
                val resultado = cancion.deleteCancion(idCancionSeleccionada)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idAlbum = Album.idAlbumSeleccionado
                showListView(idAlbum)
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