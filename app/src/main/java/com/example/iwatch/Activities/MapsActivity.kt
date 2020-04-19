package com.example.iwatch.Activities

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.iwatch.Entities.Cinema
import com.example.iwatch.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private var room = Cinema()
private var conv = Convert()

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        room = intent.getSerializableExtra("room") as Cinema

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera

        mMap.uiSettings.isZoomControlsEnabled = true
        val cinema = room.longitude?.toDouble()?.let { LatLng(room.latitude!!.toDouble(), it) }

        mMap.addMarker(cinema?.let { MarkerOptions().position(it).title("Marker in cinema") })
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cinema))


        setUpMap()

    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


    // ask user permission
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

    }
}
