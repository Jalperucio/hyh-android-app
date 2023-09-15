package com.jalper.myfirstapp.lessonsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jalper.myfirstapp.databinding.ItemLanguageBinding
import com.jalper.myfirstapp.lessonsapp.model.Language

class LanguagesAdapter(
    private val languages: List<Language>,
    val onItemClicked: (Int) -> Unit,
    ) : RecyclerView.Adapter<LanguagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LanguagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguagesViewHolder, position: Int) {
        holder.render(languages[position])

        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }

    override fun getItemCount() = languages.size

}