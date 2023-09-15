package com.jalper.myfirstapp.hello2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ActivityHello2Binding

class Hello2MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHello2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHello2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HelloFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_hello2_container, fragment).commit()
    }

    fun navigateToGreetingFragment(nameKey: String, nameValue: String){

        val fragment = GreetingFragment()
        val bundle = Bundle()
        bundle.putString(nameKey, nameValue)

        fragment.arguments = bundle

        loadFragment(fragment)

    }
}