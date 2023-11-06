package com.sample.quizapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.quizapp.ui.theme.ViewModelClass
import com.sample.quizapp.ui.theme.screens.LogInScreen
import com.sample.quizapp.ui.theme.screens.QuizPageScreen
import com.sample.quizapp.ui.theme.screens.ResultScreen


@Composable
fun NavGraph(
   view: ViewModelClass,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavRoute.LogInScreen.route) {
        composable(route = NavRoute.LogInScreen.route) {
            LogInScreen(userName = view.userName){
                navController.navigate(NavRoute.QuizScreen.route){
                    navController.popBackStack((NavRoute.LogInScreen.route), true)
                }
            }
        }
        composable(route = NavRoute.QuizScreen.route) {
            QuizPageScreen(
                view.selectedOption,
                view.onOptionSelected,
                view.buttonEnable,
                view.time,
                { view.timerRestart() },
                { view.updateScore() },
                view.questionLength,
                { view.updateQuestionLength() },
                view.checkLength,
                view.correctAnswer,
                ) {

                navController.navigate(NavRoute.ResultScreen.route){
                    navController.popBackStack((NavRoute.QuizScreen.route), true)
                }
            }

        }
        composable(route = NavRoute.ResultScreen.route) {
            ResultScreen(view.userName, view.score, { view.clearState() }){
                navController.navigate(NavRoute.LogInScreen.route){
                    navController.popBackStack((NavRoute.ResultScreen.route), true)
                }
            }
        }
    }

}