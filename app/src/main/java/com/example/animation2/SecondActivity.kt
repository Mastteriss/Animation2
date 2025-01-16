package com.example.animation2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var productsUpdate: MutableList<Product>? = mutableListOf()
    private var products: MutableList<Product> = ProductDB.products
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerRV: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        toolbar = findViewById(R.id.toolbar)
        recyclerRV = findViewById(R.id.recyclerRV)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        setSupportActionBar(toolbar)
        recyclerRV.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(products)
        recyclerRV.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        recyclerRV.setHasFixedSize(true)
        adapter?.setOnProductClickListener(object :
            CustomAdapter.OnProductClickListener {
            override fun onProductClick(product: Product, position: Int) {
                val dialogBuilder = AlertDialog.Builder(this@SecondActivity)
                dialogBuilder.setTitle("Внимание!")
                dialogBuilder.setMessage("выберите действие:")
                dialogBuilder.setPositiveButton("В корзину") { _, _ ->
                    productsUpdate?.add(product)
                    Toast.makeText(applicationContext, "Продукт в корзине", Toast.LENGTH_LONG).show()
                }
                dialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
                    dialog.cancel()
                }
                dialogBuilder.create().show()
            }
        })

        floatingActionButton.setOnClickListener{
            floatingActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            val intent = Intent(this, ThreeActivity::class.java)
            intent.putExtra("productsUpdate", productsUpdate as ArrayList<Product>)
            startActivity(intent)
        }
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