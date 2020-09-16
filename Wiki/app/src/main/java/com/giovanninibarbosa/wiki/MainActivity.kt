package com.giovanninibarbosa.wiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers;

class MainActivity : AppCompatActivity() {

    var listView: ListView? = null
    val person = mutableListOf<String>()
    var peopleAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)
        peopleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, person)
        listView?.adapter = peopleAdapter

        val api = StarWarsApi()
        api.loadPeople()
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ people ->
                person.add("${"  Nome: "}${people.name}  ${"\n"}  ${"Altura: "}${people.height}  ${"\n"}  ${"Gênero: "}${people.gender}  ${"\n"}  ${"Peso: "}${people.mass}")

            }, { e ->
                e.printStackTrace()
            }, {
                peopleAdapter!!.notifyDataSetChanged()

            })
    }

}