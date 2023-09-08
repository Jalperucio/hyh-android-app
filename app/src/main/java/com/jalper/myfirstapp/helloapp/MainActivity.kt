package com.jalper.myfirstapp.helloapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.jalper.myfirstapp.R.id
import com.jalper.myfirstapp.R.layout

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout.activity_main)

        val button = findViewById<Button>(id.btn_button)
        val etName = findViewById<EditText>(id.et_main_name)

        button.setOnClickListener {

            val intent = Intent(this, GreetingActivity::class.java)


            if(etName.text.isNotBlank()){
                intent.putExtra("NOMBRE", etName.text.toString())
            }

            startActivity(intent)

        }

    }
}