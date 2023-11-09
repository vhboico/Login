package com.example.login.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.components.ButtonEdit
import com.example.login.listener.Listener
import com.example.login.ui.theme.Shape
import com.example.login.ui.theme.black
import com.example.login.ui.theme.blue
import com.example.login.ui.theme.blue_normal
import com.example.login.ui.theme.gray
import com.example.login.ui.theme.green
import com.example.login.ui.theme.white
import com.example.login.viewmodel.ViewModelAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModelAuth: ViewModelAuth = hiltViewModel()
) {

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
                .verticalScroll(rememberScrollState())
        ) {
            ConstraintLayout {

                val (login, txt, edtEmail, edtPassword, button, txtForgot, txtNew, face, gmail) = createRefs()

                Text(
                    text = "Login",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.constrainAs(login) {
                        top.linkTo(parent.top, 130.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                Text(
                    text = "Sign in to continue",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.constrainAs(txt) {
                        top.linkTo(login.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp, 40.dp, 50.dp, 0.dp)
                        .constrainAs(edtEmail) {
                            top.linkTo(txt.bottom)
                        },
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
                        .padding(50.dp, 10.dp, 50.dp, 0.dp)
                        .constrainAs(edtPassword) {
                            top.linkTo(edtEmail.bottom)
                        },
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
                viewModelAuth.login(email, password, object : Listener {
                    override fun onSuccess(message: String, screen: String) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        navController.navigate(screen)
                    }

                    override fun onFailure(error: String) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                })
                }, text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .padding(50.dp, 15.dp, 50.dp, 10.dp)
                        .constrainAs(button) {
                            top.linkTo(edtPassword.bottom)
                        }
                )

                TextButton(
                    onClick = { navController.navigate("recoverPassword")},
                    modifier = Modifier.constrainAs(txtForgot) {
                        top.linkTo(button.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text(
                        text = "Forgot your password?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = white
                    )
                }

                TextButton(
                    onClick = { navController.navigate("signUpScreen") },
                    modifier = Modifier.constrainAs(txtNew) {
                        top.linkTo(txtForgot.bottom, 80.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text(
                        text = "Don't have an account? SignUp!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = white
                    )
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = blue_normal),
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp)
                        .constrainAs(face) {
                            top.linkTo(txtNew.bottom, 30.dp)
                            start.linkTo(parent.start, 60.dp)
                        }
                ) {
                    Text(
                        text = "Facebook",
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = white),
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp)
                        .constrainAs(gmail) {
                            top.linkTo(txtNew.bottom, 30.dp)
                            start.linkTo(face.end, 40.dp)
                        }
                ) {
                    Text(
                        text = "Gmail",
                        fontWeight = FontWeight.Bold,
                        color = black
                    )
                }

            }
        }
    }
}
