package com.example.trainings.data.training

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.trainings.domain.models.Training


@Dao
interface TrainingDao {
    @Query("SELECT * FROM Trainings")
    suspend fun getAll(): List<Training>

    @Insert
    suspend fun insert(training: Training)

    @Delete
    suspend fun delete(training: Training)

    @Update
    suspend fun update(training: Training)

    @Query("SELECT COUNT(*) FROM Trainings")
    suspend fun getCount(): Int

    @Query("SELECT * FROM Trainings LIMIT :limit OFFSET :offset")
    suspend fun getItems(offset: Int, limit: Int): List<Training>?

    @Query("SELECT * FROM Trainings WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Training?

    @Query("DELETE FROM Trainings WHERE id = :id")
    suspend fun deleteById(id: Int)
}

