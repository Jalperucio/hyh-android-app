package com.jalper.myfirstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jalper.myfirstapp.databinding.ActivityMenuBinding
import com.jalper.myfirstapp.fragmentsexampleapp.FragmentsExampleMainActivity
import com.jalper.myfirstapp.helloapp.MainActivity
import com.jalper.myfirstapp.lessonsapp.LessonActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeListeners()
    }



    private fun initializeListeners() {
        binding.btnMenuHelloButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnMenuLessonButton.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        binding.btnMenuFragmentsExampleButton.setOnClickListener {
            startActivity(Intent(this, FragmentsExampleMainActivity::class.java))
        }
    }
}