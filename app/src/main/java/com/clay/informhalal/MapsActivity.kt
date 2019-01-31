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
//        mapFragment.setmy
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
            var lokasi:LatLng = LatLng(-10.16572447010728,123.5985479298927)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(
                        -10.1669901,123.5972566
                    ))
                    .title("RUMAH MAKAN SEDERHANA")
                    .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.logoh)
                    )
            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(
                        -10.155912,123.630829
                    ))
                    .title("RUMAH MAKAN PADANG 2")
                    .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.logoh)
                    )
            )
//        marker.
        mMap.moveCamera (
                CameraUpdateFactory.newLatLngZoom(
                    lokasi,
                    15.0f
                )
            )
        }

}
