package com.hilguener.marvelsuperheroes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hilguener.marvelsuperheroes.presentation.home.MainScreen
import com.hilguener.marvelsuperheroes.presentation.signin.SignInScreen
import com.hilguener.marvelsuperheroes.presentation.signup.SignUpScreen
import com.hilguener.marvelsuperheroes.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("sign_in_screen") {
            SignInScreen(navController = navController)
        }
        composable("sign_up_screen") {
            SignUpScreen(navController = navController)
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}
