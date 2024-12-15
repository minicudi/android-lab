package com.example.trainings.presentation.instructions

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.example.trainings.presentation.components.TrainingButton
import com.example.trainings.presentation.components.TrainingOutlinedButton
import com.example.trainings.utils.Utils.trainingClick
import kotlinx.coroutines.launch

@Composable
fun InstructionScreen(navController: NavController) {
    val viewModel: InstructionsViewModel = hiltViewModel()

    val imageLoader = ImageLoader.Builder(viewModel.context)
        .components {
            if (SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val firstIndex = scrollState.firstVisibleItemIndex
    val firstOffset = scrollState.firstVisibleItemScrollOffset
    LaunchedEffect(firstIndex) {
        if (firstOffset != 0) {
            scope.launch {
                scrollState.animateScrollToItem(firstIndex + 1)
            }

        } else {
            scope.launch {
                scrollState.animateScrollToItem(firstIndex)
            }
        }
    }

    Scaffold(
        topBar = {
            InstructionsTopAppBar(
                text = viewModel.training.value?.title ?: "Упражнение",
                onBack = { navController.navigateUp() }
            )
        }
    ) {
        LazyRow(
            state = scrollState,
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.training.value?.exercises ?: listOf()) {
                Card(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.9).dp)) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .navigationBarsPadding()
                    ) {
                        Text(it.title, style = MaterialTheme.typography.displaySmall)
                        if (it.gifUrl != null || it.photoUrl != null) {
                            AsyncImage(
                                imageLoader = imageLoader,
                                model = it.gifUrl ?: it.photoUrl,
                                contentDescription = "GIF Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp)),
                            )
                        }
                        it.instructions.forEach { inst ->
                            Text(inst.title)
                            Text(inst.description)
                        }
                        Text("Количество повторений: ${it.repetitions}")
                    }
                }
            }
            item {
                Column (modifier = Modifier.fillMaxSize() ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Тренировка\nзавершена!", style = MaterialTheme.typography.displaySmall)
                    Spacer(modifier = Modifier.height(50.dp))

                    TrainingButton(
                        text = "Сделать запись о тренировке"
                    ) {
                        viewModel.saveTrainingDate()
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionsTopAppBar(text: String, onBack: () -> Unit) {
    TopAppBar(navigationIcon = {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "назад",
            modifier = Modifier.trainingClick { onBack() })
    }, title = {
        Text(text)
    })
}
