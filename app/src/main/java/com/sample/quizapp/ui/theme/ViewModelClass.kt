package com.sample.quizapp.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ViewModelClass : ViewModel() {
    var userName = mutableStateOf("")
    var time = mutableStateOf(60)
    val selectedOption = mutableStateOf("")
    val onOptionSelected = mutableStateOf("")
    val buttonEnable = mutableStateOf(false)
    var score = mutableStateOf(0)
    val correctAnswer = mutableStateOf(false)
    val questionLength = mutableStateOf(0)
    val checkLength = mutableStateOf(0)
    fun updateScore() {
        score.value = score.value + 10
    }

    fun updateQuestionLength() {
        questionLength.value++
    }

    fun timerRestart() {
        time.value = 60
    }

    fun clearState() {
        userName.value = ""
        time.value = 60
        selectedOption.value = ""
        onOptionSelected.value = ""
        buttonEnable.value = false
        score.value = 0
        correctAnswer.value = false
        questionLength.value = 0
        checkLength.value = 0
    }
}