package com.clay.informhalal

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.app.Activity
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import java.io.IOException




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnJawa = findViewById<Button>(R.id.buttonJawa)
        val btnMadura = findViewById<Button>(R.id.buttonMadura)
        val btnPadang = findViewById<Button>(R.id.buttonPadang)
        val btnLain = findViewById<Button>(R.id.buttonLainLain)

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
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("maps.googleapis.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("place")
                .appendPath("textsearch")
                .appendPath("json")
                .appendQueryParameter("query", "Rumah Makan")
                .appendQueryParameter("location", "-10.1749436,123.5446778")
                .appendQueryParameter("rankedBy", "distance")
                .appendQueryParameter("keyword", "halal")
                .appendQueryParameter("key", "AIzaSyAEjt_g770Ad3uShs286UXLaB6eQo8oySk")
            val myUrl = builder.build().toString()
            println("myUrl = ${myUrl}")

            val rest = requestHandler.readingRest(this, myUrl)

            var gson = Gson()
            var data = gson.fromJson(rest, googlePlace::class.java)
            Log.d("data.status","${data.status}")
            Log.d("data.results.size", "${data.results.size}")

        }


    }

    fun openMapsActivity(context: Context, json: String): Unit {
        val i = Intent(context, MapsActivity::class.java)
        i.putExtra("json", json)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

}
