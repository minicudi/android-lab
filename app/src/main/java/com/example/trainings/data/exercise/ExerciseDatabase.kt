package com.example.trainings.data.exercise

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainings.domain.models.Exercise
import com.example.trainings.domain.models.InstructionListConverter
import com.example.trainings.domain.models.MuscleGroupListConverter

@Database(entities = [Exercise::class,], version = 1)
@TypeConverters(MuscleGroupListConverter::class, InstructionListConverter::class,)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}
