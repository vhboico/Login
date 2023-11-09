package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.view.ListScreen
import com.example.login.view.LoginScreen
import com.example.login.view.RecoverPassword
import com.example.login.view.SignUpScreen
import com.example.login.viewmodel.ViewModelAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModelAuth: ViewModelAuth = hiltViewModel()

            NavHost(navController = navController, startDestination = "loginScreen"){
                composable("loginScreen"){
                    LoginScreen(navController)
                }
                composable("signUpScreen"){
                    SignUpScreen(navController, viewModelAuth)
                }
                composable("listScreen"){
                    ListScreen(navController)
                }
                composable("recoverPassword"){
                    RecoverPassword(navController, viewModelAuth)
                }
            }
        }
    }
}
