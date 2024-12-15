package com.example.trainings.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.trainings.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.checkExercises()
        delay(500L)
        navController.navigate(AppScreens.Main.route)
    }
}