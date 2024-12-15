package com.example.trainings.presentation.training

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.trainings.domain.models.Training
import com.example.trainings.navigation.AppScreens
import com.example.trainings.utils.Utils.trainingClick
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TrainingScreen(navController: NavController) {
    val viewModel: TrainingViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TrainingTopAppBar(
                onAdd = { navController.navigate(AppScreens.AddTraining.route) },
                onRefresh = { viewModel.reloadState() }
            )
        }
    ) {
        LaunchedEffect(Unit) {
            viewModel.reloadState()
            viewModel.loadMoreTrainings()
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                CalendarView(viewModel.dates)
            }
            items(viewModel.trainings.value) {
                TrainingCard(it, onClick = {
                    navController.navigate(
                        AppScreens.Instructions.route.replace(
                            "{id}",
                            "${it.id}"
                        )
                    )
                }, onDelete = { trainingId ->
                    viewModel.deleteTraining(trainingId)
                })
            }
            item {
                LaunchedEffect(Unit) {
                    viewModel.loadMoreTrainings()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingTopAppBar(onAdd: () -> Unit, onRefresh: () -> Unit) {
    TopAppBar(
        title = { Text("Тренировка") },
        actions = {
            // Кнопка добавления
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить упражнение",
                modifier = Modifier.trainingClick { onAdd() }
            )
            // Кнопка обновления
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Обновить тренировки",
                modifier = Modifier.trainingClick { onRefresh() }
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrainingCard(training: Training, onClick: () -> Unit, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() } // Обработчик нажатия для всей карточки
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = training.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    // Используем distinct() для исключения повторяющихся тегов
                    training.exercises
                        .flatMap { it.muscleGroup }
                        .distinct() // Удаляем дублирующиеся теги
                        .forEach { muscleGroup ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground)
                            ) {
                                Text(
                                    muscleGroup.displayName,
                                    color = MaterialTheme.colorScheme.background,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 3.dp)
                                )
                            }
                        }
                }
            }
            // Кнопка удаления
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить тренировку",
                tint = Color.Red,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDelete(training.id) } // Обработчик удаления
            )
        }
    }
}


@Composable
fun CalendarView(dates: List<LocalDate>) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val formatter = DateTimeFormatter.ofPattern("LLLL yyyy").withLocale(java.util.Locale("ru"))

        Text(
            text = currentDate.format(formatter).capitalize(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            for (day in listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")) {
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        val daysInMonth = currentDate.lengthOfMonth()
        val firstDayOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value

        var dayIndex = 1

        Column {
            for (row in 0 until 6) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (col in 0 until 7) {
                        if (row == 0 && col < dayOfWeek - 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        } else if (dayIndex <= daysInMonth) {
                            val dateToCheck = currentDate.withDayOfMonth(dayIndex)
                            val isDateInList = dates.contains(dateToCheck)

                            Text(
                                text = dayIndex.toString(),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .background(
                                        if (isDateInList) MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.3f
                                        ) else Color.Transparent
                                    ),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isDateInList) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                            )
                            dayIndex++
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { currentDate = currentDate.minusMonths(1) },
                modifier = Modifier.width(150.dp) // Устанавливаем фиксированную ширину
            ) {
                Text("Предыдущий месяц", maxLines = 2)
            }
            Spacer(Modifier.weight(1f)) // Добавляем пространство между кнопками
            Button(
                onClick = { currentDate = currentDate.plusMonths(1) },
                modifier = Modifier.width(150.dp) // Устанавливаем ту же ширину
            ) {
                Text("Следующий месяц", maxLines = 2)
            }
        }
    }
}


