package com.example.movcompjagf

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMaps : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps)
        solicitarPermisos()

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener {
            irCarolina()
        }
        solicitarPermisos()
        iniciarLogicaMapa()
    }

    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            if (googleMap != null) {
                mapa = googleMap
                establecerConfiguracionesMapa()
                irCarolina()

                val quicentro = LatLng(-0.17578089462922689, -78.47921791517774)
                val titulo = "Quicentro"
                val marcador = anadirMarcador(quicentro, titulo)
                marcador.tag = "quicentro"
                val poliLineaUno = googleMap.addPolyline(
                    PolylineOptions().clickable(true).add(
                        LatLng(-0.18358583442011503, -78.48137411993424),
                        LatLng(-0.1841997045940818, -78.48752538158854),
                        LatLng(-0.19208161101681576, -78.4845885405831)
                    )
                )
                poliLineaUno.tag = "linea-1" // <- ID
                val poligonoUno = googleMap.addPolygon(
                    PolygonOptions().clickable(true).add(
                        LatLng(-0.18240096798129674, -78.4881753202204),
                        LatLng(-0.17644990581919331, -78.48544035046119),
                        LatLng(-0.18024845635921932, -78.4781217739759)
                    )
                )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2" // <- ID
                escucharListeners()
            }
        }
    }

    fun establecerConfiguracionesMapa() {
        val contexto = this.applicationContext
        with(mapa) { // Verificar que el mapa existe (if(mapa != null))
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                uiSettings.isMyLocationButtonEnabled == true // no tenemos aun permisos
                mapa.isMyLocationEnabled == true // no tenemos aun permisos
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun irCarolina() {
        val carolina = LatLng(-0.18714810337461324, -78.4840832417561)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions().position(latLng).title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisoFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        val tienePermisos = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, // Contexto
                arrayOf( // Arreglo de Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 // Codigo de peticion de los permisos
            )
        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag // ID
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolyLineClickListener ${it}")
            it.tag // ID
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag // ID
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }
}