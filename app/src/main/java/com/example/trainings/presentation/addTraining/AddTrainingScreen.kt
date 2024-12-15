package com.example.trainings.presentation.addTraining

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.trainings.domain.models.MuscleGroup
import com.example.trainings.presentation.components.TrainingButton
import com.example.trainings.presentation.components.TrainingOutlinedButton
import com.example.trainings.presentation.components.TrainingOutlinedTextBox
import com.example.trainings.utils.Utils.trainingClick

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTrainingScreen(navController: NavController) {
    val viewModel: AddTrainingViewModel = hiltViewModel()

    Scaffold(
        topBar = { AddTrainingTopAppBar(onBack = { navController.navigateUp() }) }
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    TrainingOutlinedTextBox(
                        label = "Название тренировки",
                        value = viewModel.title.value,
                        onValueChange = { viewModel.title.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(MuscleGroup.entries) {
                            Card(
                                modifier = Modifier.trainingClick {
                                    if (it in viewModel.selectedMuscleGroup.value) {
                                        viewModel.selectedMuscleGroup.value -= it
                                    } else {
                                        viewModel.selectedMuscleGroup.value += it
                                    }
                                },
                                colors = CardDefaults.cardColors().copy(
                                    containerColor =
                                    if (it in viewModel.selectedMuscleGroup.value) {
                                        Color.White
                                    } else {
                                        MaterialTheme.colorScheme.surfaceContainer
                                    }
                                )
                            ) {
                                Text(
                                    it.displayName,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 3.dp),
                                    if (it in viewModel.selectedMuscleGroup.value) {
                                        Color.Black
                                    } else {
                                        Color.White
                                    }
                                )
                            }
                        }
                    }
                }
                items(
                    (viewModel.selectedExercises.value + viewModel.exercises.value).distinct()
                        .filter { it.muscleGroup.containsAll(viewModel.selectedMuscleGroup.value) }) {
                    val showDialog = remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .alpha(
                                if (it in viewModel.selectedExercises.value) {
                                    1f
                                } else {
                                    0.7f
                                }
                            )
                            .trainingClick {
                                if (it in viewModel.selectedExercises.value) {
                                    viewModel.selectedExercises.value -= it
                                } else {
                                    showDialog.value = true
                                }
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(it.title)
                            Text(it.description)
                            FlowRow {
                                it.muscleGroup.forEach { group ->
                                    Text(
                                        group.displayName,
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 3.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                    if (showDialog.value)
                        RepetitionsDialog(
                            it.repetitions,
                            onSave = { rec ->
                                if (rec != null) {
                                    if (it in viewModel.selectedExercises.value) {
                                        viewModel.selectedExercises.value -= it
                                    } else {
                                        viewModel.selectedExercises.value += it.copy(repetitions = rec)
                                    }
                                }
                                showDialog.value = false
                            })
                }
                item {
                    LaunchedEffect(Unit) {
                        viewModel.loadMore()
                    }
                    Spacer(modifier = Modifier.height(75.dp))
                }
            }
            TrainingButton(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth(),
                isEnabled = viewModel.title.value != "" && viewModel.selectedExercises.value.size > 0,
                text = "Добавить"
            ) {
                viewModel.saveExercise()
                navController.navigateUp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrainingTopAppBar(onBack: () -> Unit) {
    TopAppBar(navigationIcon = {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "назад",
            modifier = Modifier.trainingClick { onBack() }.padding(10.dp))
    }, title = {
        Text("Создание тренировки")
    })
}

@Composable
fun RepetitionsDialog(_repetitions: Int, onSave: (Int?) -> Unit) {
    val repetitions = remember { mutableStateOf("$_repetitions") }
    Dialog(onDismissRequest = {}) {
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Добавление упражнения")
                TrainingOutlinedTextBox(
                    label = "Введите количество повторений",
                    onValueChange = { repetitions.value = it },
                    value = repetitions.value
                )
                Row {
                    TrainingOutlinedButton(text = "Отмена", modifier = Modifier.weight(1f)) {
                        onSave(
                            null
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.1f))
                    TrainingButton(
                        text = "Сохранить",
                        isEnabled = repetitions.value.toIntOrNull() != null,
                        modifier = Modifier.weight(1f),
                    ) { onSave(repetitions.value.toInt()) }
                }
            }
        }
    }
}