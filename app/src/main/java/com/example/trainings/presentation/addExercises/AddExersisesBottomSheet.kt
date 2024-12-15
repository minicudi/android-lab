package com.example.trainings.presentation.addExercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trainings.domain.models.Exercise
import com.example.trainings.domain.models.MuscleGroup
import com.example.trainings.presentation.components.TrainingButton
import com.example.trainings.presentation.components.TrainingOutlinedTextBox
import com.example.trainings.utils.Utils.trainingClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExercisesBottomSheet(
    exercises: List<Exercise>,
    loadMore: () -> Unit,
    onSave: (List<Int>) -> Unit,
    onDismiss: () -> Unit,
) {
    val selectedExercises: MutableState<List<Int>> = remember { mutableStateOf(listOf()) }
    val exerciseSearchText = remember { mutableStateOf("") }
    val muscleGroupSearch: MutableState<List<MuscleGroup>> = remember { mutableStateOf(listOf()) }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                item {
                    TrainingOutlinedTextBox(
                        value = exerciseSearchText.value,
                        onValueChange = { exerciseSearchText.value = it },
                        label = "Поиск по названию",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    LazyRow {
                        items(MuscleGroup.entries) {
                            Card(
                                modifier = Modifier.trainingClick {
                                    muscleGroupSearch.value =
                                        if (muscleGroupSearch.value.contains(it)) {
                                            muscleGroupSearch.value - it
                                        } else {
                                            muscleGroupSearch.value + it
                                        }
                                },
                                colors = CardDefaults.cardColors().copy(
                                    if (it in muscleGroupSearch.value)
                                        MaterialTheme.colorScheme.primaryContainer
                                    else Color.Green
                                )
                            ) {
                                Card {
                                    Text(
                                        it.displayName,
                                        modifier = Modifier.padding(
                                            vertical = 3.dp,
                                            horizontal = 5.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                items(exercises.filter { it.title.toLowerCase().contains(exerciseSearchText.value) }
                    .filter { it.muscleGroup.containsAll(muscleGroupSearch.value) }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .trainingClick {
                                selectedExercises.value =
                                    if (selectedExercises.value.contains(it.id)) {
                                        selectedExercises.value - it.id
                                    } else {
                                        selectedExercises.value + it.id
                                    }
                            },
                        colors = CardDefaults.cardColors().copy(
                            if (it.id in selectedExercises.value)
                            MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                        Text(it.title)
                        Row {
                            it.muscleGroup.forEach {
                                Text("${it.displayName} ")
                            }
                        }}

                    }
                }
                item {
                    LaunchedEffect(Unit) {
                        loadMore()
                    }
                }
            }
            TrainingButton(
                isEnabled = selectedExercises.value.size > 0,
                text = "Добавить упражнения",
                onClick = { onSave(selectedExercises.value) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}