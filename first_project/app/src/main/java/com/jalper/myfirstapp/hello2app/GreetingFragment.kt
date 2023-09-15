package com.jalper.myfirstapp.hello2app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.FragmentGreetingBinding

class GreetingFragment : Fragment() {

    private lateinit var binding: FragmentGreetingBinding
    private lateinit var name: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        /**Recogemos la variables de los argumentos o ponemos "Anónim@" como valor por defecto*/
        name = arguments?.getString(HelloFragment.NAME)?: getString(R.string.greeting_name_anonymous)

        binding = FragmentGreetingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Esta es la manera de incluir variables dentro de cadenas de strings.xml*/
        binding.tvGreetingName.text = getString(R.string.greeting_name, name)
    }


}