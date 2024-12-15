package com.example.trainings.data.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trainings.domain.models.Exercise


@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercises")
    suspend fun getAll(): List<Exercise>

    @Insert
    suspend fun insert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Query("SELECT COUNT(*) FROM Exercises")
    suspend fun getCount(): Int

    @Query("SELECT * FROM Exercises LIMIT :limit OFFSET :offset")
    suspend fun getItems(offset: Int, limit: Int): List<Exercise>?

    @Query("SELECT * FROM Exercises WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Exercise?
}

