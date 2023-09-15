package com.jalper.myfirstapp.helloapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jalper.myfirstapp.R.id
import com.jalper.myfirstapp.R.layout

class HelloMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout.activity_hello)

        val button = findViewById<Button>(id.btn_button)
        val etName = findViewById<EditText>(id.et_main_name)

        button.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            if (etName.text.isNotBlank()) {
                intent.putExtra("NOMBRE", etName.text.toString())
            }
            startActivity(intent)
        }
    }
}