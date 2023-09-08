package com.jalper.myfirstapp.helloapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jalper.myfirstapp.R.id
import com.jalper.myfirstapp.R.layout

class GreetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_greeting)

        val tvName = findViewById<TextView>(id.tv_greeting_name)

        val name = intent.extras?.getString("NOMBRE","Chico anónimo") ?: "Chica anónima"

        tvName.text = "Bienvenido, Mr.$name"

    }
}