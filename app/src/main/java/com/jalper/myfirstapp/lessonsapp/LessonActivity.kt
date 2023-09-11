package com.jalper.myfirstapp.lessonsapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.jalper.myfirstapp.databinding.ActivityLessonBinding
import com.jalper.myfirstapp.databinding.DialogAddLessonBinding
import com.jalper.myfirstapp.lessonsapp.model.Language
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()
    }

    private fun initUI() {
        binding.rvLessonLanguages.adapter = LanguagesAdapter(languagesList) { position: Int -> selectLanguage(position)}
        binding.rvLessonLessons.adapter = LessonsAdapter(lessonsList) { position: Int -> removeItem(position) }
    }

    private fun initListeners() {
        binding.fabLessonsAddButton.setOnClickListener {
            showDialog()
        }
    }

//      --Ejemplos de función convertida en lambda--
//    fun saludar(name: String) { println("Hola, $name") }
//    val saludarLambda = { name:String ->  println("Hola, $name")}


    private fun showDialog() {
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

            updateLessonsListView()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun removeItem(position: Int){
        //Da fallos por culpa del índice si se han realizado filtros primero
        //Se deja por motivos de avance académico
        lessonsList.removeAt(position)
        updateLessonsListView()
    }

    private fun selectLanguage(position: Int) {
        languagesList[position].isSelected = !languagesList[position].isSelected
        updateLanguagesListView()
        updateLessonsListView()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun updateLessonsListView() {
        val selectedLanguages: List<Language> = languagesList.filter { it.isSelected }
        val showableLessons = lessonsList.filter { selectedLanguages.contains(it.language) }
        //val showableLessons = lessonsList.filter { it.language in selectedLanguages }


        (binding.rvLessonLessons.adapter as LessonsAdapter).lessons = showableLessons

        binding.rvLessonLessons.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateLanguagesListView() {
        binding.rvLessonLanguages.adapter?.notifyDataSetChanged()
    }

}