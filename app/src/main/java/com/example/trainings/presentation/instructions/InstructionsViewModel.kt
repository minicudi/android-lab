package com.example.trainings.presentation.instructions

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import coil3.gif.GifDecoder
import com.example.trainings.data.dates.DatesDatabase
import com.example.trainings.data.training.TrainingDatabase
import com.example.trainings.domain.models.Date
import com.example.trainings.domain.models.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstructionsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val trainingDatabase: TrainingDatabase,
    val datesDatabase: DatesDatabase,
    @ApplicationContext val context: Context,
) : ViewModel() {

    val id: Int = checkNotNull(savedStateHandle["id"])
    val training: MutableState<Training?> = mutableStateOf(null)

    init {
        getTraining()
    }
    fun saveTrainingDate(){
        viewModelScope.launch {
            datesDatabase.datesDao().insert(Date(date =  java.util.Date()))
        }
    }


    fun getTraining() {
        viewModelScope.launch {
            training.value = trainingDatabase.trainingDao().getById(id)
        }
    }
}