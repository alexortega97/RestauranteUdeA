package com.example.restauranteudea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnVer.setOnClickListener{
            var intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}
