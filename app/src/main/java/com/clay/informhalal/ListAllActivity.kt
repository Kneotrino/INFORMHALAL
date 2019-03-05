package com.clay.informhalal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson


class ListAllActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("CariGo",query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("CariOn",newText)

        if (TextUtils.isEmpty(newText)) {
            mAdapter!!.filter.filter("")
        } else {
            mAdapter!!.filter.filter(newText)
        }
        return true
    }

    var mSearchView: SearchView? = null
     var mListView: ListView? = null
     var mArrayList: ArrayList<googlePlace.Result>? = ArrayList<googlePlace.Result>()
     var mAdapter: ArrayAdapter<googlePlace.Result>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_all)

        val latitude = intent.getDoubleExtra("lat", -10.162353)
        val longitude = intent.getDoubleExtra("long", 123.5915637)
        val myLokasi = LatLng(latitude,longitude)

        mListView = findViewById<ListView>(R.id.listAllMap)
        mSearchView = findViewById<SearchView>(R.id.cariRM)


        mArrayList!!.addAll(addData("padang.json"))
        mArrayList!!.addAll(addData("jawa.json"))
        mArrayList!!.addAll(addData("madura.json"))



        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1
            , mArrayList!!)
        mListView!!.adapter = mAdapter
        mListView!!.setOnItemClickListener { _, _, position, _ ->
            val get = mArrayList!!.get(position)
            val i = Intent(this, infoActivity::class.java)
            i.putExtra("json", "mockMenu.json")
            i.putExtra("nama", get.name)
            i.putExtra("alamat", get.formatted_address)
            i.putExtra("rating", get.rating.toString())
            i.putExtra("id", get.place_id)
            i.putExtra("myLat", myLokasi.latitude)
            i.putExtra("myLng", myLokasi.longitude)
            i.putExtra("lat", get.geometry.location.lat )
            i.putExtra("long", get.geometry.location.lng )
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
        setupSearchView()
    }

    private fun setupSearchView() {
        mSearchView!!.setIconifiedByDefault(false)
        mSearchView!!.setOnQueryTextListener(this)
        mSearchView!!.setSubmitButtonEnabled(true)
    }
    private fun addData(jsonSource: String) : List<googlePlace.Result> {
        val jsonFromAsset = requestHandler.loadJSONFromAsset(this, jsonSource)
        val gson = Gson()
        val data = gson.fromJson(jsonFromAsset, googlePlace::class.java)
        Log.d("data.results.size", "${data.results.size}")
        val list = data.results
        return list
    }
}
