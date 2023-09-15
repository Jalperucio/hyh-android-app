package com.jalper.myfirstapp.lessonsapp.model

import com.jalper.myfirstapp.lessonsapp.model.Language.Android

data class Lesson(
    var name: String,
    val language: Language = Android
)
