package com.example.login.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
fun RecoverPassword(
    navController: NavController,
    viewModelAuth: ViewModelAuth = hiltViewModel()
) {

    var email by remember {
        mutableStateOf("")
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
            modifier = Modifier.fillMaxSize(),
        ) {

            ConstraintLayout {

                val (txt1, txt2, edtEmail, btn) = createRefs()

                Text(
                    text = "Recover password",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.constrainAs(txt1) {
                        top.linkTo(parent.top, 130.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                Text(
                    text = "To recover your password, enter your email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.constrainAs(txt2) {
                        top.linkTo(txt1.bottom, 10.dp)
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
                            top.linkTo(txt2.bottom, 120.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
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

                ButtonEdit(onClick = {
                    viewModelAuth.recoverPassword(email, object : Listener {
                        override fun onSuccess(message: String, screen: String) {
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            navController.navigate(screen)
                        }

                        override fun onFailure(error: String) {
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }
                    })
                }, text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .padding(50.dp, 15.dp, 50.dp, 10.dp)
                        .constrainAs(btn) {
                            top.linkTo(edtEmail.bottom)
                        }
                )
            }
        }
    }
}
