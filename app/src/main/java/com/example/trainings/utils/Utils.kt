package com.example.trainings.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object Utils {
    fun Double.toStringFormat() :String {
        return  DecimalFormat("#.00") .format(this)
    }

    @Composable
    fun Modifier.trainingClick(onClick: () -> Unit): Modifier {
        val interactionSource = remember { MutableInteractionSource() }
        return this.clickable(
            indication = null,
            interactionSource = interactionSource
        ) { onClick() }
    }

    fun Date.formatDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(this)
    }
    fun Date.formatDateDayMonth(): String {
        val dateFormat = SimpleDateFormat("dd-MM", Locale.getDefault())
        return dateFormat.format(this)
    }

    fun Date.toLocalDate (): LocalDate {
        return this.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
    fun LocalDate.toDate(): Date {
        return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

}