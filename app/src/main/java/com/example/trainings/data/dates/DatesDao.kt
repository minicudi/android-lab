package com.example.trainings.data.dates

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.time.LocalDate
import java.util.Date

@Dao
interface DatesDao {
    @Query("SELECT * FROM Dates")
    suspend fun getAll(): List<com.example.trainings.domain.models.Date>

    @Insert
    suspend fun insert(date: com.example.trainings.domain.models.Date)

    @Delete
    suspend fun delete(date: com.example.trainings.domain.models.Date)
}

