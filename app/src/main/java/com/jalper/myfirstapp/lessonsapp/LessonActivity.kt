package com.jalper.myfirstapp.lessonsapp

import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.jalper.myfirstapp.databinding.ActivityLessonBinding
import com.jalper.myfirstapp.databinding.DialogAddLessonBinding
import com.jalper.myfirstapp.lessonsapp.model.Language.Android
import com.jalper.myfirstapp.lessonsapp.model.Language.Flutter
import com.jalper.myfirstapp.lessonsapp.model.Language.IOS
import com.jalper.myfirstapp.lessonsapp.model.Lesson

class LessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonBinding

    private val languagesList = listOf(Android, Flutter, IOS)

    private val lessonsList = mutableListOf<Lesson>(
        Lesson("Arquitectura", IOS),
        Lesson("UX", Android),
        Lesson("Variables", Flutter),
        Lesson("Estilos")
    )

    val leccion: Lesson = Lesson("Vistas", Android)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()
    }

    fun initUI() {
        binding.rvLessonLanguages.adapter = LanguagesAdapter(languagesList)
        binding.rvLessonLessons.adapter = LessonsAdapter(lessonsList)
    }

    fun initListeners() {
        binding.fabLessonsAddButton.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog() {
        val dialog = Dialog(this)

        val dialogBinding = DialogAddLessonBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.etDialogAddLessonName.addTextChangedListener { editText ->
            dialogBinding.btnDialogAddLesson.isEnabled = editText?.isNotBlank() ?: false
        }

        dialogBinding.btnDialogAddLesson.setOnClickListener {

            val name = dialogBinding.etDialogAddLessonName.text.toString()

            val selectedId = dialogBinding.rgDialogAddLessonLanguage.checkedRadioButtonId
            val radioButtonSelected = dialogBinding.root.findViewById<RadioButton>(selectedId)
            val languageSelected = when (radioButtonSelected.text) {
                "Android" -> Android
                "iOS" -> IOS
                "Flutter" -> Flutter
                else -> Android
            }

            lessonsList.add(Lesson(name, languageSelected))

            /*TODO - Disclaimer:
            * Comentamos en clase que al añadir un elemento al listado no habría cambios
            * gráficos si no avisamos al adaptador de estos cambios, pero luego vimos
            * en directo que esto no era del t odo cierto y sí se actualizaba el listado
            * Esto es por utilizar un mutableList (ya lo veremos el próximo día).
            *
            * Como este caso no es habitual y solo es un ejemplo intermedio antes de que
            * veamos la implementación nivel pro de las ReyclerView dejo la siguiente
            * función añadida para que os acostumbréis a que hay que avisar al adaptador
            * tras realizar cambios sobre sus listas
            */
            updateLessonsListView()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun updateLessonsListView() {
        binding.rvLessonLessons.adapter?.notifyDataSetChanged()
    }
}