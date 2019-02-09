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
                .appendQueryParameter("key", "AIzaSyAbEL810DZ5tkDdB31yOmX3h9ocbrbQj4g")
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
        val i = Intent(this, MapsActivity::class.java)
        i.putExtra("json", json)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }


//    class MyAdapter(private val context: Context, private val items: Array<modelRumahMakan>) : BaseAdapter() {
//
//        override fun getCount(): Int {
//            return items.size
//        }
//
//        override fun getItem(position: Int): Any {
//            return items[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return 0
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//            var listItem = convertView
//            if (listItem == null)
//                listItem = LayoutInflater.from(context).inflate(
//                    R.layout.layout_rm,
//                    parent,
//                    false
//                )
//
//            val textNama = listItem?.findViewById<TextView>(R.id.textViewNama)
//            val textKel = listItem?.findViewById<TextView>(R.id.textViewKelurahan)
//            val textAlamat = listItem?.findViewById<TextView>(R.id.textViewAlamat)
//            val textJamBuka = listItem?.findViewById<TextView>(R.id.textViewWaktu)
//            val textKet = listItem?.findViewById<TextView>(R.id.textViewKet)
//            val btnMaps = listItem?.findViewById<Button>(R.id.buttonMaps)
//
//            textNama?.text = items[position].nama
//            textAlamat?.text = items[position].alamat
//            textKel?.text = items[position].kelurahan
//            textJamBuka?.text = items[position].getJadwal()
//            textKet?.text = items[position].jenis
//
//            btnMaps?.setOnClickListener({
////                val i = Intent(context, MapsActivity::class.java)
////                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////                i.putExtra("lokasi",items[position].lokasi)
////                context.startActivity(i)
//                val uri = "https://www.google.com/maps/place/Rumah+Makan+Persada/@-10.1753701,123.5923084,16z/data=!4m5!3m4!1s0x2c569ca1a2b435e7:0x51eba4ddf406b17d!8m2!3d-10.1753701!4d123.5966858"
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                intent.setPackage("com.google.android.apps.maps");
//                context.startActivity(intent)
//            })
//            return listItem!!
//        }
//    }

}
