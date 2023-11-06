package com.sample.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sample.quizapp.ui.theme.QuizAppTheme
import com.sample.quizapp.ui.theme.ViewModelClass
import com.sample.quizapp.ui.theme.screens.LogInScreen
import com.sample.quizapp.ui.theme.screens.QuizPageScreen
import com.sample.quizapp.ui.theme.screens.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val view by viewModels<ViewModelClass>()
                    when (view.screenCheck.value) {
                        "quizScreen" ->
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
                                view.screenCheck
                            )

                        "loginScreen" -> LogInScreen(userName = view.userName, view.screenCheck)

                        else -> ResultScreen(view.userName, view.score) { view.clearState() }
                    }
                }
            }
        }
    }
}
