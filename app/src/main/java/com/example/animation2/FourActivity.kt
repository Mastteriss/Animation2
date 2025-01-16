package com.example.animation2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FourActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var products: MutableList<Product> = mutableListOf()

    private lateinit var toolbarFA: Toolbar
    private lateinit var recyclerFARV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_four)


        toolbarFA = findViewById(R.id.toolbarFA)
        recyclerFARV = findViewById(R.id.recyclerFARV)

        setSupportActionBar(toolbarFA)

        products = intent.getSerializableExtra("products") as MutableList<Product>
        recyclerFARV.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(products)
        recyclerFARV.adapter = adapter
        recyclerFARV.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.exitMenu -> {
                finishAffinity()
                Toast.makeText(applicationContext, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}