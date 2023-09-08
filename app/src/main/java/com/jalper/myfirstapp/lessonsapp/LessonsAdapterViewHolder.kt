package com.jalper.myfirstapp.lessonsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jalper.myfirstapp.databinding.ItemLessonBinding
import com.jalper.myfirstapp.lessonsapp.model.Lesson

class LessonsAdapter(private val lessons: List<Lesson>): RecyclerView.Adapter<LessonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.render(lessons[position])
    }

    override fun getItemCount() = lessons.size
}



class LessonsViewHolder(private val binding: ItemLessonBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(lesson: Lesson){
        binding.tvItemLesson.text = lesson.name
    }
}