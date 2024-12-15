package com.example.trainings.navigation

sealed class AppScreens(val route: String) {
    object Main : AppScreens("main/")
    object Splash : AppScreens("splash/")
    object AddTraining : AppScreens("add/")
    object Instructions : AppScreens("instruction/{id}/")
}