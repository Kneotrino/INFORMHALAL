package com.clay.informhalal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var jsonSource = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        jsonSource = intent.getStringExtra("json")
        latitude = intent.getDoubleExtra("lat",-10.162353)
        longitude = intent.getDoubleExtra("long", 123.5915637)

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
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap

//            var myLokasi:LatLng = LatLng(-10.16572447010728,123.5985479298927)
            val myLokasi = LatLng(latitude,longitude)

            val jsonFromAsset = requestHandler.loadJSONFromAsset(this, jsonSource)
            val gson = Gson()
            val data = gson.fromJson(jsonFromAsset, googlePlace::class.java)
            Log.d("data.results.size", "${data.results.size}")

            val list = data.results

            val map:HashMap<Marker,googlePlace.Result> = hashMapOf()

            this.mMap.getUiSettings().setMyLocationButtonEnabled(false)
            this.mMap.setMyLocationEnabled(true)

            for (i in list) {
                        val addMarker = mMap.addMarker(
                            MarkerOptions()
                                .position(
                                    LatLng(
                                        i.geometry.location.lat,
                                        i.geometry.location.lng
                                    )
                                )
                                .title(i.name)
                                .icon(
                                    BitmapDescriptorFactory.fromResource(R.drawable.logoh)
                                )

                        )
                        map.put(addMarker,i)
                    }

                mMap.moveCamera (
                        CameraUpdateFactory.newLatLngZoom(
                            myLokasi,
                            15.0f
                        )
                    )

            mMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->
                    val get = map.get(marker)
                    val i = Intent(this, infoActivity::class.java)
                    i.putExtra("json", get?.reference)
                    i.putExtra("nama", get?.name)
                    i.putExtra("alamat", get?.formatted_address)
                    i.putExtra("rating", get?.rating.toString())
                    i.putExtra("id", get?.place_id)
                    i.putExtra("myLat", myLokasi.latitude)
                    i.putExtra("myLng", myLokasi.longitude)
                    i.putExtra("lat", get?.geometry?.location?.lat )
                    i.putExtra("long", get?.geometry?.location?.lng )
//                    i.putExtra("geometry", get!!.geometry)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
            false
            })
        }

}
