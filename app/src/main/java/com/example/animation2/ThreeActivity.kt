package com.example.animation2

import android.annotation.SuppressLint
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThreeActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var products: MutableList<Product> = mutableListOf()
    private lateinit var layoutTA:ConstraintLayout
    private lateinit var toolbarTA: Toolbar
    private lateinit var recyclerTARV: RecyclerView
    private lateinit var floatingActionButtonTAFA: FloatingActionButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_three)

        toolbarTA = findViewById(R.id.toolbarTA)
        recyclerTARV = findViewById(R.id.recyclerTARV)
        floatingActionButtonTAFA = findViewById(R.id.floatingActionButtonTAFA)

        setSupportActionBar(toolbarTA)
        products = intent.getSerializableExtra("productsUpdate") as MutableList<Product>
        recyclerTARV.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(products)
        recyclerTARV.adapter = adapter



    }

    override fun onResume() {
        super.onResume()
        recyclerTARV.setHasFixedSize(true)
        adapter?.setOnProductClickListener(object :
            CustomAdapter.OnProductClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onProductClick(product: Product, position: Int) {
                val dialogBuilder = AlertDialog.Builder(this@ThreeActivity)
                dialogBuilder.setTitle("Внимание!")
                dialogBuilder.setMessage("выберите действие:")
                dialogBuilder.setPositiveButton("Удалить из корзины") { _, _ ->
                    products.remove(product)
                    adapter?.notifyDataSetChanged()
                }
                dialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
                    dialog.cancel()
                }
                dialogBuilder.create().show()
            }
        })
        floatingActionButtonTAFA.setOnClickListener{
            floatingActionButtonTAFA.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            val intent = Intent(this, FourActivity::class.java)
            intent.putExtra("products", products as ArrayList<Product>)
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