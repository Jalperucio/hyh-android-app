package com.jalper.myfirstapp.hello2app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jalper.myfirstapp.databinding.FragmentHelloBinding

class HelloFragment : Fragment() {

    private lateinit var binding: FragmentHelloBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHelloBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Para acceder desde el binding de una vista a elementos (btn_button, et_main_name) que
         * están dentro de un módulo xml metido a través de <include> accedemos a ellos
         * mencionando tras el binding el id que le hayamos puesto a esa etiqueta <include> (en
         * este caso 'module_send_name'.
         */
        binding.moduleSendName.btnButton.setOnClickListener {
            val nameEditText = binding.moduleSendName.etMainName.text
            if (nameEditText.isNullOrBlank()) {
                (activity as Hello2MainActivity).navigateToGreetingFragment(NAME, nameEditText.toString())
            }
        }
    }

    /** Tod o lo que se incluye en un companion object puede ser accedido
     * desde otra clase invocando esta sin instanciarla. Para obtener la
     * constante NAME podemos acceder a ella desde cualquier lugar
     * utilizando Fragment.NAME
     */
    companion object {

        //Los nombres de constantes en SNAKE_UPPER_CASE
        const val NAME = "clave_nombre"
    }
}