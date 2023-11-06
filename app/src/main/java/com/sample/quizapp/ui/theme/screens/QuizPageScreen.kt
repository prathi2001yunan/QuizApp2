package com.sample.quizapp.ui.theme.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.quizapp.ui.theme.navigationBarPadding
import com.sample.quizapp.ui.theme.statusBarPadding
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizPageScreen(
    onSelected: MutableState<String>,
    onOptionSelected: MutableState<String>,
    buttonEnable: MutableState<Boolean>,
    timer: MutableState<Int>,
    timerRestart: () -> Unit,
    updateScore: () -> Unit,
    length: MutableState<Int>,
    updateLength: () -> Unit,
    checkLength: MutableState<Int>,
    correctAnswer: MutableState<Boolean>,
    navigate: () -> Unit
) {
    val configuration = LocalConfiguration.current
    LaunchedEffect(key1 = true) {
        while (timer.value > 0) {
            delay(1000L)
            timer.value--
            if (timer.value == 0) {
                if (checkLength.value != length.value) {
                    updateLength()
                } else {
                    navigate()
                }
                timerRestart()
            }
        }
    }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            QuizScreenLandScape(
                onSelected = onSelected,
                onOptionSelected = onOptionSelected,
                buttonEnable = buttonEnable,
                timer = timer,
                timerRestart = timerRestart,
                updateScore = updateScore,
                length = length,
                updateLength = updateLength,
                checkLength = checkLength,
                correctAnswer = correctAnswer,
                navigate

            )
        }

        else -> {
            QuizScreenPortrait(
                onSelected = onSelected,
                onOptionSelected = onOptionSelected,
                buttonEnable = buttonEnable,
                timer = timer,
                timerRestart = timerRestart,
                updateScore = updateScore,
                length = length,
                updateLength = updateLength,
                checkLength = checkLength,
                correctAnswer = correctAnswer,
                navigate
            )
        }
    }
}

@Composable
fun QuizScreenLandScape(
    onSelected: MutableState<String>,
    onOptionSelected: MutableState<String>,
    buttonEnable: MutableState<Boolean>,
    timer: MutableState<Int>,
    timerRestart: () -> Unit,
    updateScore: () -> Unit,
    length: MutableState<Int>,
    updateLength: () -> Unit,
    checkLength: MutableState<Int>,
    correctAnswer: MutableState<Boolean>,
   navigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        Arrangement.Center,
        Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            QuizTimer(length, checkLength, timer, 0.1f)
            QuizQuesDisplay(length, checkLength, height = 0.8f)
        }
        Column(
            modifier = Modifier.weight(1f),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            QuizOptionDisplay(
                onSelected,
                onOptionSelected,
                buttonEnable,
                correctAnswer,
                length,
                0.8f
            )
            QuizScreenButton(
                timerRestart,
                onOptionSelected,
                onSelected,
                buttonEnable,
                updateScore,
                updateLength,
                length,
                correctAnswer,
                checkLength,
                navigate,
                0.6f
            )
        }
    }
}

@Composable
fun QuizScreenPortrait(
    onSelected: MutableState<String>,
    onOptionSelected: MutableState<String>,
    buttonEnable: MutableState<Boolean>,
    timer: MutableState<Int>,
    timerRestart: () -> Unit,
    updateScore: () -> Unit,
    length: MutableState<Int>,
    updateLength: () -> Unit,
    checkLength: MutableState<Int>,
    correctAnswer: MutableState<Boolean>,
    navigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.01f))
        QuizTimer(length, checkLength, timer, 0.05f)
        QuizQuesDisplay(length, checkLength, height = 0.3f)
        QuizOptionDisplay(
            onSelected,
            onOptionSelected,
            buttonEnable,
            correctAnswer,
            length,
            0.8f

        )
        Spacer(modifier = Modifier.fillMaxHeight(0.01f))
        QuizScreenButton(
            timerRestart,
            onOptionSelected,
            onSelected,
            buttonEnable,
            updateScore,
            updateLength,
            length,
            correctAnswer,
            checkLength,
            navigate,
            0.5f
        )
    }
}

@Composable
fun QuizQuesDisplay(length: MutableState<Int>, checkLength: MutableState<Int>, height: Float) {
    Card(
        modifier = Modifier
            .fillMaxHeight(height)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            Color(0xFFE3E2E6)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 40.dp)
    ) {
        val quesList = listOf<String>(
            "Developers who write in Kotlin are called ?",
            "Kotlin are what type of language",
            "Kotlin is mainly used for ?"
        )
        checkLength.value = quesList.size - 1
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${length.value + 1}) ${quesList[length.value]}",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 40.sp
            )
        }
    }
}

@Composable
fun QuizOptionDisplay(
    selectedOption: MutableState<String>,
    onOptionSelected: MutableState<String>,
    buttonEnable: MutableState<Boolean>,
    correctAnswer: MutableState<Boolean>,
    length: MutableState<Int>,
    height: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        val optionList1 = listOf<List<String>>(
            listOf<String>("Kotlin developer", "Java developer", "Python developer"),
            listOf(
                "Statically typed programming language",
                "Dynamically typed programming language",
                "Both"
            ),
            listOf("Android developing", "Web developing", "Ios developing")
        )
        val answerList = listOf<String>(
            "Kotlin developer",
            "Statically typed programming language",
            "Android developing"
        )
        val optionList = optionList1[length.value]
        optionList.forEach() { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shadow(20.dp)
                    .background(
                        Color(0xFFFAF8FC),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .selectable(
                        selected = (text == selectedOption.value),
                        onClick = {
                            correctAnswer.value = answerList[length.value] == text
                            selectedOption.value = text
                            onOptionSelected.value = text
                            buttonEnable.value = true
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption.value),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        correctAnswer.value = answerList[length.value] == text
                        selectedOption.value = text
                        onOptionSelected.value = text
                        buttonEnable.value = true
                    }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
fun QuizTimer(
    length: MutableState<Int>,
    checkLength: MutableState<Int>,
    timer: MutableState<Int>,
    height: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .fillMaxHeight(height),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(90.dp)
                .shadow(20.dp),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {

            Text(
                text = "${length.value + 1} / ${checkLength.value + 1} ",
                fontSize = 25.sp,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {

            Text(
                text = timer.value.toString(),
                fontSize = 25.sp,
            )
        }
    }
}

@Composable
fun QuizScreenButton(
    timerRestart: () -> Unit,
    onOptionSelected: MutableState<String>,
    selectedOption: MutableState<String>,
    buttonEnable: MutableState<Boolean>,
    updateScore: () -> Unit,
    updateLength: () -> Unit,
    length: MutableState<Int>,
    correctAnswer: MutableState<Boolean>,
    checkLength: MutableState<Int>,
    navigate: () -> Unit,
    height: Float
) {
    Button(
        onClick = {
            timerRestart()
            if (correctAnswer.value) {
                updateScore()
                correctAnswer.value = false
            }
            selectedOption.value = ""
            if (checkLength.value != length.value) {
                onOptionSelected.value = ""
                buttonEnable.value = false
                updateLength()
            } else {
              navigate()
            }
        }, enabled = buttonEnable.value,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(height)
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = if (checkLength.value == length.value) "Sumbit" else "Next",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold
        )
    }
}