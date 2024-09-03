package com.hilguener.marvelsuperheroes.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.MarvelSuperHeroesTheme
import com.hilguener.marvelsuperheroes.R
import com.hilguener.marvelsuperheroes.data.repository.ConnectivityRepository
import com.hilguener.marvelsuperheroes.presentation.home.MyApp
import com.hilguener.marvelsuperheroes.presentation.splash.SplashScreen
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val connectivityRepository: ConnectivityRepository by inject()

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            connectivityRepository.isConnected.collect { isConnected ->
                val message =
                    if (isConnected) {
                        getString(R.string.connected_to_internet)
                    } else {
                        getString(R.string.no_internet_connection)
                    }
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
        setContent {
            MarvelSuperHeroesTheme {
                val navController = rememberNavController()
                Scaffold {
                    NavHost(
                        navController = navController,
                        startDestination = "splash_screen",
                    ) {
                        composable("splash_screen") {
                            SplashScreen(
                                onNavigateToMyApp = {
                                    navController.navigate("my_app") {
                                        popUpTo("splash_screen") { inclusive = true }
                                    }
                                },
                            )
                        }
                        composable("my_app") {
                            MyApp()
                        }
                    }
                }
            }
        }
    }
}
