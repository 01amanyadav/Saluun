package com.example.saluun

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saluun.pages.AuthViewModel
import com.example.saluun.pages.HomePage
import com.example.saluun.pages.LoginPage
import com.example.saluun.pages.SignUpPage


@Composable
fun AuthNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable(route = "home") {
            HomePage(Modifier, navController, authViewModel = authViewModel)
        }
        composable(route = "login") {
            LoginPage(Modifier, navController, authViewModel = authViewModel)
        }
        composable(route = "signup") {
            SignUpPage(Modifier, navController, authViewModel = authViewModel)
        }
    })
}