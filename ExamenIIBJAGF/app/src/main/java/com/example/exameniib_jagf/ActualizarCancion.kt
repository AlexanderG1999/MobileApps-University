package com.example.exameniib_jagf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.exameniib_jagf.bd.DbCancion

class ActualizarCancion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_cancion)

        /*val idCancion = verCanciones.idCancionSeleccionada
        var cancion = DbCancion(null, "", "", "", "", 0, this)
        cancion = cancion.getCancionById(idCancion)

        var id = findViewById<EditText>(R.id.et_upd_idCanc)
        id.setText(cancion.getidCancion().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombCanc)
        nombre.setText(cancion.getnombreCancion())
        var autor = findViewById<EditText>(R.id.et_upd_autorCanc)
        autor.setText(cancion.getautorCancion())
        var calificacion = findViewById<EditText>(R.id.et_upd_califCanc)
        calificacion.setText(cancion.getcalificacion())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaCanc)
        fecha.setText(cancion.getfechaPublicacion())
        var fk = findViewById<EditText>(R.id.et_upd_idAlbCanc)
        fk.setText(cancion.getidAlbum().toString())

        val btn_actualizar_cancion = findViewById<Button>(R.id.btn_upd_cancion)
        btn_actualizar_cancion.setOnClickListener {
            cancion.setnombreCancion(nombre.text.toString())
            cancion.setautorCancion(autor.text.toString())
            cancion.setcalificacion(calificacion.text.toString())
            cancion.setfechaPublicacion(fecha.text.toString())
            cancion.setidAlbum(fk.text.toString().toInt())
            val resultado = cancion.updateCancion()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }*/
    }

    fun cleanEditText() {
        var id = findViewById<EditText>(R.id.et_upd_idCanc)
        id.setText("")
        var nombre = findViewById<EditText>(R.id.et_upd_nombCanc)
        nombre.setText("")
        var autor = findViewById<EditText>(R.id.et_upd_autorCanc)
        autor.setText("")
        var calificacion = findViewById<EditText>(R.id.et_upd_califCanc)
        calificacion.setText("")
        var fecha = findViewById<EditText>(R.id.et_upd_fechaCanc)
        fecha.setText("")
        var fk = findViewById<EditText>(R.id.et_upd_idAlbCanc)
        fk.setText("")
        id.requestFocus()
    }
}