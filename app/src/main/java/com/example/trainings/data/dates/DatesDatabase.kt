package com.example.trainings.data.dates

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.trainings.utils.Utils.toDate
import com.example.trainings.utils.Utils.toLocalDate
import java.time.LocalDate
import java.util.Date

@Database(entities = [com.example.trainings.domain.models.Date::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class DatesDatabase : RoomDatabase() {
    abstract fun datesDao(): DatesDao
}


class DateConverter {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it)}
    }
}