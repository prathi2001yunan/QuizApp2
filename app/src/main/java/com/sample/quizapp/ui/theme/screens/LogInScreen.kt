package com.sample.quizapp.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.quizapp.R
import com.sample.quizapp.ui.theme.navigationBarPadding
import com.sample.quizapp.ui.theme.statusBarPadding
import androidx.compose.material3.TopAppBarColors as TopAppBarColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    userName: MutableState<String>,
    navigate: () -> Unit
) {
    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = MaterialTheme.statusBarPadding(),
                    bottom = MaterialTheme.navigationBarPadding()
                ),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.crypton_tech ),
            contentDescription = "",
            Modifier.size(150.dp) )
        LoginPageCardView(userName, navigate)

    }
}


@Composable
private fun LoginPageCardView(
    userName: MutableState<String>,
    navigate: () -> Unit
) {
    val configuration = LocalConfiguration.current
    var paddingWidth = 0.dp
    var cardHeight = 0.0f
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            paddingWidth = 150.dp
            cardHeight = 1f
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
            Text(
                text = "Crypton Tech Quiz",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF474B50)
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            LoginPageText(userName)
            Spacer(modifier = Modifier.height(10.dp))
            LoginPageButton(userName, navigate)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginPageText(userName: MutableState<String>) {
    OutlinedTextField(
        value = userName.value,
        onValueChange = { userName.value = it },
        label = { Text(text = "Enter Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 1.dp)
            .padding(horizontal = 30.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Black
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
private fun LoginPageButton(
    userName: MutableState<String>,
    navigate: () -> Unit
) {
    Button(
        onClick = {navigate()},
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
        colors = ButtonDefaults.buttonColors(Color(0xFF056FC4))
    ) {
        Text(text = "Start", fontSize = 16.sp)
    }
}