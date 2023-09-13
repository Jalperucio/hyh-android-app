package com.jalper.myfirstapp.helloprefs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jalper.myfirstapp.databinding.ActivityHelloPrefsBinding

class HelloPrefsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelloPrefsBinding
    private var preferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(PreferenceKeys.PREF_KEY, Context.MODE_PRIVATE)

        binding.etActivityHelloPrefsName.setText(
            preferences?.getString(PreferenceKeys.NAME_KEY, "") ?: "" // Operador elvis
        )

        binding.btnActivityHelloPrefsSave.setOnClickListener {
            val name = binding.etActivityHelloPrefsName.text.toString()

            if (name.isNotBlank()) {
                val editor = preferences?.edit()
                editor?.putString(PreferenceKeys.NAME_KEY, name)
                editor?.apply()
            }
        }

        preferences
            ?.edit()
            ?.putBoolean(PreferenceKeys.PREF_PAGE_VIEWED_KEY, true)
            ?.apply()
    }
}