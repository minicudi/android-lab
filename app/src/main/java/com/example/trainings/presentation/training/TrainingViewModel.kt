package com.example.trainings.presentation.training

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainings.data.dates.DatesDatabase
import com.example.trainings.utils.Utils.toLocalDate
import com.example.trainings.data.training.TrainingDatabase
import com.example.trainings.domain.models.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    val trainingDatabase: TrainingDatabase,
    val datesDatabase: DatesDatabase,
) : ViewModel() {


    private var trainingsCurrentPage = 0
    private val trainingsPageSize = 5
    var trainingsCount: Int? = null
    val trainings: MutableState<List<Training>> = mutableStateOf(listOf())

    var dates = runBlocking { datesDatabase.datesDao().getAll().map { it.date!!.toLocalDate() } }
    fun reloadState(){
        trainingsCurrentPage = 0
        trainings.value = listOf()
        dates = runBlocking { datesDatabase.datesDao().getAll().map { it.date!!.toLocalDate() } }
    }

    fun loadMoreTrainings() {
        if (trainingsCount ?: Int.MAX_VALUE > trainingsPageSize * trainingsCurrentPage)
            viewModelScope.launch {
                trainingsCount = trainingDatabase.trainingDao().getCount()
                trainings.value = (trainings.value + (trainingDatabase.trainingDao()
                    .getItems(
                        trainingsPageSize * trainingsCurrentPage,
                        trainingsPageSize * trainingsCurrentPage + trainingsPageSize
                    )
                    ?: listOf())).toMutableList()
                trainingsCurrentPage += 1
            }
    }
    fun deleteTraining(trainingId: Int) {
        viewModelScope.launch {
            try {
                trainingDatabase.trainingDao().deleteById(trainingId) // Удаляем тренировку из базы данных по id
                trainings.value = trainings.value.filter { it.id != trainingId } // Обновляем состояние списка
            } catch (e: Exception) {
                // Обработка ошибок, если необходимо
            }
        }
    }
}