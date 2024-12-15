package com.example.trainings.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Date

@Entity(tableName = "Dates")
data class Date(
    @androidx.room.PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: Date?,
)
