package com.example.trainings.presentation.addTraining

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainings.data.exercise.ExerciseDatabase
import com.example.trainings.data.training.TrainingDatabase
import com.example.trainings.domain.models.Exercise
import com.example.trainings.domain.models.MuscleGroup
import com.example.trainings.domain.models.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTrainingViewModel @Inject constructor(
    val exerciseDatabase: ExerciseDatabase,
    val trainingDatabase: TrainingDatabase,
) : ViewModel() {

    private var currentPage = 0
    private val pageSize = 10
    var exercisesCount: Int? = null
    val exercises: MutableState<List<Exercise>> = mutableStateOf(listOf())

    val title = mutableStateOf("")
    val selectedExercises: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val selectedMuscleGroup: MutableState<List<MuscleGroup>> = mutableStateOf(listOf())

    fun loadMore() {
        if (exercisesCount ?: Int.MAX_VALUE > pageSize * currentPage)
            viewModelScope.launch {
                exercisesCount = exerciseDatabase.exerciseDao().getCount()
                exercises.value = (exercises.value + (exerciseDatabase.exerciseDao()
                    .getItems(pageSize * currentPage, pageSize * currentPage + pageSize)
                    ?: listOf())).toMutableList()
                currentPage += 1
            }
    }

    fun saveExercise() {
        viewModelScope.launch {
            trainingDatabase.trainingDao().insert(
                Training(
                    title = title.value,
                    exercises = selectedExercises.value,
                )
            )
        }
    }

}