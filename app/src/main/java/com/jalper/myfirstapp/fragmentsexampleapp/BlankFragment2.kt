package com.jalper.myfirstapp.fragmentsexampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jalper.myfirstapp.R

class BlankFragment2 : Fragment() {

//TODO poner viewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val edad = arguments?.getInt("parametro2", 18)


        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    companion object {

    }
}