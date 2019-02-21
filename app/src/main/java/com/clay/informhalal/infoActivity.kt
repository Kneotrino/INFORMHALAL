package com.clay.informhalal

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson


class infoActivity : AppCompatActivity() {
    var jsonSource = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        jsonSource = intent.getStringExtra("json")

        val nama = findViewById<TextView>(R.id.textNama)
        val alamat = findViewById<TextView>(R.id.textAlamat)
        val rating = findViewById<TextView>(R.id.textRating)

        nama.setText(intent.getStringExtra("nama"))
        alamat.setText(intent.getStringExtra("alamat"))
        rating.setText(intent.getStringExtra("rating"))

        val asset = requestHandler.loadJSONFromAsset(this, "mockMenu.json")
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
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            return items.size
        }
    }


}
