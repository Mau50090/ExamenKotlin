package com.mauricio.examen_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val LogIn = LogIn()
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().add(R.id.mainActivity,LogIn).commit()
    }
}