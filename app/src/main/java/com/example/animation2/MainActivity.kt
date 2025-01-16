package com.example.animation2

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var layoutCL:ConstraintLayout
    private lateinit var startBTN:Button
    private lateinit var imageViewIV:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        layoutCL = findViewById(R.id.layoutCL)
        startBTN = findViewById(R.id.startBTN)
        imageViewIV = findViewById(R.id.imageViewIV)
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        imageViewIV.startAnimation(fadeInAnimation)
        imageViewIV.startAnimation(slideInAnimation)
        imageViewIV.animate().apply {
                duration = 2000
                rotationY(360f)
            }.withEndAction {
                imageViewIV.animate().apply {
                    duration = 2000
                    rotationY(360f)
                }.withEndAction {
                    imageViewIV.animate().apply {

                    }
                }


        }
        backgroundAnimation()


        startBTN.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            finish()
        }

    }

    private fun backgroundAnimation(){
        val animation: AnimationDrawable = layoutCL.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(1000)
            start()
        }
    }
}