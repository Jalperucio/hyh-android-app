package com.jalper.myfirstapp.lessonsapp

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ItemLessonBinding
import com.jalper.myfirstapp.lessonsapp.model.Language
import com.jalper.myfirstapp.lessonsapp.model.Lesson

class LessonsAdapter(
    var lessons: List<Lesson>,
    val onItemClicked: (Int) -> Unit,
): RecyclerView.Adapter<LessonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.render(lessons[position])
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }

    override fun getItemCount() = lessons.size
}



class LessonsViewHolder(private val binding: ItemLessonBinding): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun render(lesson: Lesson){

        val language = binding.root.context.getString(when (lesson.language) {
            Language.Android ->  R.string.language_android
            Language.IOS ->  R.string.language_ios
            Language.Flutter ->  R.string.language_flutter
        })

        binding.tvItemLesson.text = ("${lesson.name} - $language")
    }
}