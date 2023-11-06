package com.sample.quizapp.ui.theme.navigation

sealed class NavRoute(val route: String) {
    object LogInScreen: NavRoute("login_screen")
    object QuizScreen: NavRoute("quiz_screen")
    object ResultScreen: NavRoute("result_screen")
}
