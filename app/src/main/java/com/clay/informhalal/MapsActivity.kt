package com.clay.informhalal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val bundle=intent.extras
        var lokasi:LatLng = LatLng(-10.1749436,123.5446778)

        if(bundle!=null)
        {
            lokasi = bundle.get("lokasi") as LatLng
        }

        mMap.addMarker(
            MarkerOptions()
                .position(lokasi)
                .title("WELCOME TO KUPANG SI RM HALAL")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logoh )
                )
        )

//        val kupang = LatLngBounds(
//            LatLng(-10.183333,123.583333), LatLng(-10.0, 154.0)
//        )
//        val kupang = LatLng(-10.183333,123.583333)
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(lokasi, 15f)) );

//        mMap.cameraPosition
        mMap.moveCamera (
            CameraUpdateFactory.newLatLngZoom(
                lokasi,
                15.0f
            )
        )
        }

}
