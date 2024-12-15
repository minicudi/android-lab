package com.example.trainings.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "Exercises")
data class Exercise(
    @androidx.room.PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "muscleGroup") val muscleGroup: List<MuscleGroup>,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "repetitions") val repetitions: Int =1,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "instructions") val instructions: List<Instruction>,
    @ColumnInfo(name = "gifUrl") val gifUrl: String?= null,
    @ColumnInfo(name = "photoUrl") val photoUrl: String? =null,
)


class MuscleGroupListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromMuscleGroupList(muscleGroups: List<MuscleGroup>?): String? {
        return gson.toJson(muscleGroups)
    }

    @TypeConverter
    fun toMuscleGroupList(muscleGroupString: String?): List<MuscleGroup>? {
        return if (muscleGroupString == null) null else {
            val type = object : TypeToken<List<MuscleGroup>>() {}.type
            gson.fromJson(muscleGroupString, type)
        }
    }
}
class InstructionListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromInstructionList(item: List<Instruction>?): String? {
        return gson.toJson(item)
    }

    @TypeConverter
    fun toInstructionList(item: String?): List<Instruction>? {
        return if (item == null) null else {
            val type = object : TypeToken<List<Instruction>>() {}.type
            gson.fromJson(item, type)
        }
    }
}