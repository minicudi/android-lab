package com.example.trainings.data.training

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainings.domain.models.ExerciseListConverter
import com.example.trainings.domain.models.Training

@Database(entities = [Training::class], version = 1)
@TypeConverters(ExerciseListConverter::class)
abstract class TrainingDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}
