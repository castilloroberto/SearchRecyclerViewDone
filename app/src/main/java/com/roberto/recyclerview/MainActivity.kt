package com.roberto.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.SearchEvent
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var list:List<String> = emptyList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         list = resources.getStringArray(R.array.countries_array).toList()



        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = MyAdapter()

        (rv_recyclerView.adapter as MyAdapter).setListOfCountries(list)

        et_filterable.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val listFilter = list.filter {
                    it.toString().toLowerCase(Locale.ROOT).contains(p0.toString().toLowerCase(Locale.ROOT))
                }
                (rv_recyclerView.adapter as MyAdapter).setListOfCountries(listFilter)
            }

            override fun afterTextChanged(p0: Editable?) {


            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val search = menu?.findItem(R.id.nav_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Buscar algo!!"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // TODO: 20/9/2020
                //  myAdapter.filter.filter(p0)
                val listFilter = list.filter {
                    it.toString().toLowerCase(Locale.ROOT).contains(p0.toString().toLowerCase(Locale.ROOT))
                }
                (rv_recyclerView.adapter as MyAdapter).setListOfCountries(listFilter)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}


