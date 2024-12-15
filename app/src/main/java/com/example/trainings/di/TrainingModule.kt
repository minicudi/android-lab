package com.example.trainings.di

import android.content.Context
import androidx.room.Room
import com.example.trainings.data.dates.DatesDatabase
import com.example.trainings.data.exercise.ExerciseDatabase
import com.example.trainings.data.training.TrainingDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object TrainingModule {
    @Provides
    fun provideTrainingRoomDatabase(@ApplicationContext context: Context): TrainingDatabase {
        return Room.databaseBuilder(
            context,
            TrainingDatabase::class.java, "Trainings"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideExerciseRoomDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java, "Exercises"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideDateRoomDatabase(@ApplicationContext context: Context): DatesDatabase {
        return Room.databaseBuilder(
            context,
            DatesDatabase::class.java, "Dates"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideGson() = Gson()
}
