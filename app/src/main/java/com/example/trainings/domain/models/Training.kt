package com.example.trainings.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "Trainings")
data class Training(
    @androidx.room.PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "exercises") val exercises: List<Exercise>,
    )


object ExerciseListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromExerciseList(exercises: List<Exercise>?): String? {
        return gson.toJson(exercises)
    }

    @TypeConverter
    fun toExerciseList(exerciseString: String?): List<Exercise>? {
        val listType = object : TypeToken<List<Exercise>>() {}.type
        return gson.fromJson(exerciseString, listType)
    }
}