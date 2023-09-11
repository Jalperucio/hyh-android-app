package com.jalper.myfirstapp.fragmentsexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ActivityFragmentsExampleMainBinding

class FragmentsExampleMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentsExampleMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentsExampleMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(BlankFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_main_container, fragment).commit()
    }

    fun navigateToBlankFragment2(edad: Int){

        val fragment = BlankFragment2()
        val bundle = Bundle()
        bundle.putInt("parametro", edad)
        bundle.putInt("parametro2", 2)

        fragment.arguments = bundle

        loadFragment(fragment)
    }
}