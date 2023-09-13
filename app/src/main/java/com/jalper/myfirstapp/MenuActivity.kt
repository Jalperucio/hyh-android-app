package com.jalper.myfirstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jalper.myfirstapp.chronometer.ChronometerActivity
import com.jalper.myfirstapp.coroutines.CoroutinesExampleActivity
import com.jalper.myfirstapp.databinding.ActivityMenuBinding
import com.jalper.myfirstapp.hello2app.Hello2MainActivity
import com.jalper.myfirstapp.helloapp.HelloMainActivity
import com.jalper.myfirstapp.helloprefs.HelloPrefsActivity
import com.jalper.myfirstapp.lessonsapp.LessonActivity
import com.jalper.myfirstapp.pokemonapp.PokemonActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
        initializeListeners()
    }

    private fun initializeUI(){
        //Manera tradicional
        //binding.ivSquirtleImage.setImageDrawable(context?.getDrawable(R.drawable.ic_squirtle))

        //Mediante Glide
        Glide.with(this)
            .load(R.drawable.ic_lamp)
            .into(binding.ivMenuIcon)
    }
    private fun initializeListeners() {

        binding.btnMenuHelloButton.setOnClickListener {
            startActivity(Intent(this, HelloMainActivity::class.java))
        }

        binding.btnMenuLessonButton.setOnClickListener {
            startActivity(Intent(this, LessonActivity::class.java))
        }

        binding.btnMenuFragmentsExampleButton.setOnClickListener {
            startActivity(Intent(this, Hello2MainActivity::class.java))
        }

        binding.btnMenuPokemonButton.setOnClickListener {
            startActivity(Intent(this, PokemonActivity::class.java))
        }

        binding.btnMenuHelloPrefsButton.setOnClickListener {
            startActivity(Intent(this, HelloPrefsActivity::class.java))
        }

        binding.btnMenuCoroutinesExampleButton.setOnClickListener {
            startActivity(Intent(this, CoroutinesExampleActivity::class.java))
        }

        binding.btnMenuChronometerButton.setOnClickListener {
            startActivity(Intent(this, ChronometerActivity::class.java))
        }
    }
}