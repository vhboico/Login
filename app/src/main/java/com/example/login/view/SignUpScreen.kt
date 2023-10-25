package com.example.login.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.components.ButtonEdit
import com.example.login.listener.Listener
import com.example.login.ui.theme.Shape
import com.example.login.ui.theme.black
import com.example.login.ui.theme.blue
import com.example.login.ui.theme.gray
import com.example.login.ui.theme.green
import com.example.login.ui.theme.white
import com.example.login.viewmodel.ViewModelAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModelAuth: ViewModelAuth = hiltViewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var visibility by remember {
        mutableStateOf(false)
    }

    val icon = if (visibility)
        painterResource(id = R.drawable.baseline_visibility_24)
    else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    black,
                    blue,
                    blue,
                    green
                )
            )
        ),
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Create new account",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = white
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 40.dp, 50.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = gray,
                    textColor = white,
                    cursorColor = white,
                    focusedBorderColor = white,
                    unfocusedBorderColor = white
                ),
                maxLines = 1,
                shape = Shape.medium,
                label = {
                    Text(text = "Name", color = white)
                }
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 10.dp, 50.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = gray,
                    textColor = white,
                    cursorColor = white,
                    focusedBorderColor = white,
                    unfocusedBorderColor = white
                ),
                maxLines = 1,
                shape = Shape.medium,
                label = {
                    Text(text = "Email", color = white)
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 10.dp, 50.dp, 20.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = gray,
                    textColor = white,
                    cursorColor = white,
                    focusedBorderColor = white,
                    unfocusedBorderColor = white
                ),
                maxLines = 1,
                shape = Shape.medium,
                label = {
                    Text(text = "Password", color = white)
                },
                trailingIcon = {
                    IconButton(onClick = { visibility = !visibility }) {
                        Icon(painter = icon, contentDescription = "", tint = white)
                    }
                },
                visualTransformation = if (visibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            ButtonEdit(onClick = {
                viewModelAuth.signUp(name, email, password, object : Listener {
                    override fun onSuccess(message: String, screen: String) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        navController.navigate(screen)
                    }

                    override fun onFailure(error: String) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                })
            }, text = "Create account")
        }
    }
}