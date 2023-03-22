package com.example.movcompjagf

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }

    val contenidoIntentImplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    if (result.data!!.data != null) {
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(uri, null, null, null, null, null)
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn", "Telefono ${telefono}")
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonACicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonACicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            contenidoIntentImplicito.launch(intentConRespuesta)
        }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent.setOnClickListener {
            abrirActividadParametros(CIntentExplicitoParametros::class.java)
        }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite.setOnClickListener {
            // Creando DB
            val sqliteHelper: ESqliteHelperEntrenador = ESqliteHelperEntrenador(this)
            val db: SQLiteDatabase = sqliteHelper.writableDatabase
            if (db != null) {
                Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "ERROR AL CREAR LA BD", Toast.LENGTH_LONG).show()
            }
            // Fin creacion DB

            irActividad(ECrudEntrenador::class.java)
        }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView.setOnClickListener {
            irActividad(GRecyclerView::class.java)
        }

        val botonGmaps = findViewById<Button>(R.id.btn_google_maps)
        botonGmaps.setOnClickListener {
            irActividad(HGoogleMaps::class.java)
        }

        val botonFAuth = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFAuth.setOnClickListener {
            irActividad(IFirebaseUIAuth::class.java)
        }

        val botonFirestore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFirestore.setOnClickListener {
            irActividad(JFirebaseFirestore::class.java)
        }
    }

    fun abrirActividadParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        //Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Alexander")
        intentExplicito.putExtra("apellido", "Guillin")
        intentExplicito.putExtra("edad", 23)
        intentExplicito.putExtra("entrenador", BEntrenador(1, "ash", "pueblo paleta"))
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}