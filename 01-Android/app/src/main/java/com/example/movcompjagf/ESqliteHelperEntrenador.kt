package com.example.movcompjagf

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(contexto: Context?) : SQLiteOpenHelper(
    contexto,
    "moviles.bd", // Nombre de nuestra BDD SQLite (moviles.sqlite)
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(nombre: String, descripcion: String): Boolean {
        // this.readableDatabase Lectura
        // this.writableDatabase Escritura
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoGuardar = basedatosEscritura.insert(
            "ENTRENADOR", // Tabla
            null, //
            valoresAGuardar // valores
        )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        // vall conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        // "SELECT * FROM ENTRENADOR WHERE ID = ?"
        // arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR", // TABLA
            "id=?", // id=? and nombre=? Where (podemos mandar parametros en orden)
            arrayOf( // Arreglo parametros en orden [1, "Adrian"]
                id.toString()
            )
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAAactualizar = ContentValues()
        valoresAAactualizar.put("nombre", nombre)
        valoresAAactualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR", // Nombre tabla
            valoresAAactualizar, // Valores a actualizar
            "id=?", // Clausula Where
            arrayOf(idActualizar.toString()) // Parametros clausula Where
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador {
        val baseDatosLectura = readableDatabase
        val scriptConsultaUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaUsuario, arrayOf(id.toString())
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        // LOGICA OBTENER EL USUARIO
        do {
            val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
            val nombre = resultadoConsultaLectura.getString(1) // columna indice 1 -> NOMBRE
            val descripcion =
                resultadoConsultaLectura.getString(2) // columna indice 2 -> DESCRIPCION
            if (id != null) {
                usuarioEncontrado.id = id
                usuarioEncontrado.nombre = nombre
                usuarioEncontrado.descripcion = descripcion
            }
        } while (resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}