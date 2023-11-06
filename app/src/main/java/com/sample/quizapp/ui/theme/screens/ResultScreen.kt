package com.sample.quizapp.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.quizapp.R
import com.sample.quizapp.ui.theme.navigationBarPadding
import com.sample.quizapp.ui.theme.statusBarPadding

@Composable

fun ResultScreen(
    userName: MutableState<String>,
    score: MutableState<Int>,
    clearAll: () -> Unit,
    navigate: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        val configuration = LocalConfiguration.current
        var paddingWidth = 0.dp
        var cardHeight = 0.0f
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                paddingWidth = 150.dp
                cardHeight = 0.8f
            }

            else -> {
                paddingWidth = 20.dp
                cardHeight = 0.5f
            }
        }
        Card(
            modifier = Modifier
                .fillMaxHeight(cardHeight)
                .fillMaxWidth()
                .padding(horizontal = paddingWidth),
            colors = CardDefaults.cardColors(Color(0xFFF7FAFF)),
            elevation = CardDefaults.cardElevation(defaultElevation = 40.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_thumb_up_24),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Text(
                    text = " Hello ${userName.value} your score is ${score.value}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(0.4f),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
                Button(
                    onClick = {
                        navigate()
                        clearAll() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 80.dp, vertical = 10.dp)
                        .padding(top = 0.dp),
                    enabled = userName.value.isNotEmpty(),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 20.dp,
                        pressedElevation = 40.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color(0xFF4B4C4D))
                ) {
                    Text(text = "Back", fontSize = 16.sp)
                }
            }
        }
    }
}