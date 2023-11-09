package com.example.login.view

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login.ui.theme.blue
import com.example.login.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = blue,
                title = {
                    Text(
                        text = "Welcome",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                },
            )
        }
    ) {

    }

}