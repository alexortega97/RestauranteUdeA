package com.example.restauranteudea

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val envigado = LatLng(6.177293, -75.585812)
        val sanlucas = LatLng(6.181037, -75.568873)
        val laureles = LatLng(6.246671, -75.595186)
        val molinos = LatLng(6.233161, -75.604180)
        val milla = LatLng(6.199748, -75.572685)
        val este = LatLng(6.198480, -75.556880)
        val tesoro = LatLng(6.197493, -75.559033)
        mMap.addMarker(MarkerOptions().position(envigado))
        mMap.addMarker(MarkerOptions().position(sanlucas))
        mMap.addMarker(MarkerOptions().position(laureles))
        mMap.addMarker(MarkerOptions().position(molinos))
        mMap.addMarker(MarkerOptions().position(milla))
        mMap.addMarker(MarkerOptions().position(este))
        mMap.addMarker(MarkerOptions().position(tesoro))

        mMap.setOnMarkerClickListener(this)
        mMap.uiSettings.isZoomControlsEnabled = true

        setUpMap()
    }

    private fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true

        val zoomLevel = 13.0f;

        fusedLocationClient.lastLocation.addOnSuccessListener (this) {location ->
            if(location != null){
                lastLocation = location
                val currentLanLong = LatLng(location.latitude, location.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLanLong, zoomLevel))
            }
        }
    }
}
