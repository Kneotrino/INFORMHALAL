package com.clay.informhalal

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import java.util.*


class infoActivity : AppCompatActivity() {
    var jsonSource = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        jsonSource = intent.getStringExtra("json")
        println("jsonSource = ${jsonSource}")

        val nama = findViewById<TextView>(R.id.textNama)
        val alamat = findViewById<TextView>(R.id.textAlamat)
        val rating = findViewById<TextView>(R.id.textRating)

        val urlat = intent.getDoubleExtra("lat",-10.162353)
        val urlng = intent.getDoubleExtra("long", 123.5915637)

        val mylat = intent.getDoubleExtra("myLat",-10.162353)
        val mylng = intent.getDoubleExtra("myLng", 123.5915637)

        val urLokasi = LatLng(urlat,urlng)
        val myLokasi = LatLng(mylat,mylng)

        val buttonOpenMap = findViewById<Button>(R.id.buttonOpenMap)
        val buttonOpenDirection = findViewById<Button>(R.id.buttonJalur)

        buttonOpenDirection.setOnClickListener {
            val uri = String.format(
                Locale.ENGLISH,
                "http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f",
                mylat,
                mylng,
                urlat,
                urlng
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)

        }
        buttonOpenMap.setOnClickListener {
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("search")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("query", intent.getStringExtra("nama"))
                .appendQueryParameter("query_place_id", intent.getStringExtra("id"))
            val key = builder.build().toString()
            println("key = ${key}")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(key))
            startActivity(intent)
        }


        println("myLokasi = ${myLokasi}")
        println("urLokasi = ${urLokasi}")

        nama.setText(intent.getStringExtra("nama"))
        alamat.setText(intent.getStringExtra("alamat"))
        rating.setText(intent.getStringExtra("rating"))

        val asset = requestHandler.loadJSONFromAsset(this, jsonSource)
        val menu = Gson().fromJson(asset, Menu::class.java)
        val menuResult = ArrayList(menu.results)

        val listView = findViewById<ListView>(R.id.listMenu)
        listView.adapter = MyAdapter(this,menuResult)

    }

    class MyAdapter(private val context: Context, private val items: ArrayList<Menu.Result?>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var listItem = convertView
            if (listItem == null)
                listItem = LayoutInflater.from(context).inflate(
                    android.R.layout.simple_list_item_2,
                    parent,
                    false
                )

            val text1 = listItem?.findViewById<TextView>(android.R.id.text1)
            val text2 = listItem?.findViewById<TextView>(android.R.id.text2)

            text1!!.setText(items[position]!!.menu)
            text2!!.setText(items[position]!!.Harga)
            return listItem!!


        }

        override fun getItem(position: Int): Any {
            return items[position]!!
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return items.size
        }
    }


}
