package com.example.trainings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trainings.presentation.addTraining.AddTrainingScreen
import com.example.trainings.presentation.instructions.InstructionScreen
import com.example.trainings.presentation.splash.SplashScreen
import com.example.trainings.presentation.training.TrainingScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(
            route = AppScreens.Main.route
        ) {
            TrainingScreen(navController)
        }
        composable(
            route = AppScreens.Splash.route
        ) {
            SplashScreen(navController)
        }
        composable(
            route = AppScreens.AddTraining.route
        ) {
            AddTrainingScreen(navController)
        }
        composable(route = AppScreens.Instructions.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType}))
        {
            InstructionScreen(navController)
        }
    }
}