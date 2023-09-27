package com.hiberus.handh.data.feature.services.remote.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GsonDateDeserializer: JsonDeserializer<Date>, JsonSerializer<Date> {
    private val supportedDateFormat = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSS",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd",
        "dd-MM-yyyy'T'HH:mm:ss.SSS'Z'",
        "dd-MM-yyyy'T'HH:mm:ss.SSS",
        "dd-MM-yyyy'T'HH:mm:ss",
        "dd-MM-yyyy",
        "yyyy/MM/dd'T'HH:mm:ss.SSS'Z'",
        "yyyy/MM/dd'T'HH:mm:ss.SSS",
        "yyyy/MM/dd'T'HH:mm:ss",
        "yyyy/MM/dd",
        "dd/MM/yyyy'T'HH:mm:ss.SSS'Z'",
        "dd/MM/yyyy'T'HH:mm:ss.SSS",
        "dd/MM/yyyy'T'HH:mm:ss",
        "dd/MM/yyyy",
        "yyyy.MM.dd'T'HH:mm:ss.SSS'Z'",
        "yyyy.MM.dd'T'HH:mm:ss.SSS",
        "yyyy.MM.dd'T'HH:mm:ss",
        "yyyy.MM.dd",
        "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'",
        "dd.MM.yyyy'T'HH:mm:ss.SSS",
        "dd.MM.yyyy'T'HH:mm:ss",
        "dd.MM.yyyy",
    )

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        for (format in supportedDateFormat){
            try {
                val stringDate = json?.asJsonPrimitive?.asString

                return stringDate?.let {
                    SimpleDateFormat(format, Locale.getDefault()).parse(it)
                } as Date
            } catch (_: Exception) {

            }
        }

        throw ParseException("Error parsing date", 0)
    }

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? = src?.let { nDate ->
        JsonPrimitive(
            SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss",
                Locale.getDefault()
            ).format(nDate)
        )
    }
}