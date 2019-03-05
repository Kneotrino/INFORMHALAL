package com.clay.informhalal

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import br.com.safety.locationlistenerhelper.core.LocationTracker
import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    var latitude: Double? = 0.0
    var longitude: Double? = 0.0
    var accuracy = 0f
    var myLocationText: TextView? = null
    private var locationTracker: LocationTracker? = null
    private lateinit var myReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnJawa = findViewById<Button>(R.id.buttonJawa)
        val btnMadura = findViewById<Button>(R.id.buttonMadura)
        val btnPadang = findViewById<Button>(R.id.buttonPadang)
        val btnLain = findViewById<Button>(R.id.buttonLainLain)
        myLocationText = findViewById(R.id.textViewLocation)


        btnPadang.setOnClickListener {
            openMapsActivity(this,"padang.json")
        }
        btnJawa.setOnClickListener {
            openMapsActivity(this,"jawa.json")
        }
        btnMadura.setOnClickListener {
            openMapsActivity(this,"madura.json")
        }

        btnLain.setOnClickListener {
            println("test lain")
            openListAll(this)
        }


        val broadcastReceiver = object : BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == "my.action") {
                    val locationData =
                        intent.getParcelableExtra<Parcelable>(SettingsLocationTracker.LOCATION_MESSAGE) as Location
                    latitude = locationData.latitude
                    longitude= locationData.longitude
                    accuracy = locationData.accuracy
                    myLocationText!!.text = "latitude = ${latitude} \nlongitude = ${longitude} \nAccuracy ${accuracy}"
                }

            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("my.action"))
    }

    private fun getFormattedAdrees(latitude: Double, longitude: Double) {
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("maps.googleapis.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("geocode")
                .appendPath("json")
                .appendQueryParameter("latlng", "${latitude},${longitude}")
                .appendQueryParameter("key", "AIzaSyAEjt_g770Ad3uShs286UXLaB6eQo8oySk")
            val myUrl = builder.build().toString()
            println("myUrl = ${myUrl}")
            val rest = requestHandler.readingRest(this, myUrl)
            val gson = Gson()
            val data = gson.fromJson(rest, geoCode::class.java)
            Log.d("data.status","${data.status}")
            Log.d("data.results.size", "${data.results!!.size}")
            if (data.status == "OK")
            {
                val formattedAddress = data.results!!.get(0)!!.formatted_address
                myLocationText!!.text = formattedAddress
            }
    }

    fun openMapsActivity(context: Context, json: String) {
        val i = Intent(context, MapsActivity::class.java)
        i.putExtra("json", json)
        i.putExtra("lat",latitude)
        i.putExtra("long",longitude)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

    fun openListAll(context: Context) {
        val i = Intent(context, ListAllActivity::class.java)
        i.putExtra("lat",latitude)
        i.putExtra("lng",longitude)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)

    }

    override fun onStart() {
        super.onStart()
        locationTracker = LocationTracker("my.action")
            .setInterval(50000)
            .setGps(true)
            .setNetWork(false)
            .start(baseContext, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        locationTracker!!.onRequestPermission(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
        locationTracker!!.stopLocationService(this)
    }


//            val builder = Uri.Builder()
//            builder.scheme("https")
//                .authority("maps.googleapis.com")
//                .appendPath("maps")
//                .appendPath("api")
//                .appendPath("place")
//                .appendPath("textsearch")
//                .appendPath("json")
//                .appendQueryParameter("query", "Rumah Makan")
//                .appendQueryParameter("location", "-10.1749436,123.5446778")
//                .appendQueryParameter("rankedBy", "distance")
//                .appendQueryParameter("keyword", "halal")
//                .appendQueryParameter("key", "AIzaSyAEjt_g770Ad3uShs286UXLaB6eQo8oySk")
//            val myUrl = builder.build().toString()
//            println("myUrl = ${myUrl}")
//
//            val rest = requestHandler.readingRest(this, myUrl)
//
//            var gson = Gson()
//            var data = gson.fromJson(rest, googlePlace::class.java)
//            Log.d("data.status","${data.status}")
//            Log.d("data.results.size", "${data.results.size}")

}
