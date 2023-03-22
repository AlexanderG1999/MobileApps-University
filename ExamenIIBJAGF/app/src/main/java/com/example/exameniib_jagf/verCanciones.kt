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

class verCanciones : AppCompatActivity() {
    companion object {
        var idCancionSeleccionada = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_canciones)
        val idAlbum = Album.idAlbumSeleccionado
        var cancionAux = DbCancion("","","","","","",this)

        val textViewAutor = findViewById<TextView>(R.id.tv_albumVerCanciones)
        textViewAutor.text = "Álbum: "+ cancionAux.getNameAlbum(idAlbum)

        val btnCrearCancion = findViewById<Button>(R.id.btn_crearCancion)
        btnCrearCancion.setOnClickListener {
            irActividad(Cancion::class.java)
        }
        getCancion()
        showListView(idAlbum)
    }

    fun showListView(id: String) {
        // ListView Canciones
        val objetoCancion = DbCancion("", "", "", "", "", "", this)
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
    }

    fun deleteCancion(){
        val cancion = DbCancion("", "", "", "", "", "",this)
        cancion.setidAlbum(idCancionSeleccionada.toString())

        MainActivity.databaseReference.child("Cancion").child(cancion.getidCancion().toString()).removeValue()
        Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
        finish()
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_canc -> {
                return true
            }
            R.id.mi_eliminar_canc -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun getCancion(){
        val lv_cancion = findViewById<ListView>(R.id.listView_cancion)
        lv_cancion.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var cancion = parent?.getItemAtPosition(position) as DbCancion
                idCancionSeleccionada = cancion.getidCancion().toString()
            }

        })
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta canción?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                deleteCancion()
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