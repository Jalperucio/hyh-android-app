package com.jalper.myfirstapp.lessonsapp

import androidx.recyclerview.widget.RecyclerView
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ItemLanguageBinding
import com.jalper.myfirstapp.lessonsapp.model.Language

class LanguagesViewHolder(
    private val binding: ItemLanguageBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun render(language: Language) {
        binding.tvItemLanguageName

        when(language) {
            Language.Android -> binding.tvItemLanguageName.text = binding.root.context.getString(R.string.language_android)
            Language.IOS -> binding.tvItemLanguageName.text = binding.root.context.getString(R.string.language_ios)
            Language.Flutter -> binding.tvItemLanguageName.text = binding.root.context.getString(R.string.language_flutter)
        }
    }
}
