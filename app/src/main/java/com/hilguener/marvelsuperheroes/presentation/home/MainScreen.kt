package com.hilguener.marvelsuperheroes.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hilguener.marvelsuperheroes.presentation.FavoriteScreen
import com.hilguener.marvelsuperheroes.presentation.characters.CharactersScreen
import com.hilguener.marvelsuperheroes.presentation.navigation.AppBar
import com.hilguener.marvelsuperheroes.presentation.navigation.DrawerBody
import com.hilguener.marvelsuperheroes.presentation.navigation.DrawerHeader
import com.hilguener.marvelsuperheroes.presentation.navigation.NavDrawerItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val appBarTitle = when (currentRoute) {
        "main_screen" -> "Home"
        "characters_screen" -> "Characters"
        "favorites_screen" -> "Favorites"
        else -> ""
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                HorizontalDivider()
                DrawerBody(
                    items = listOf(
                        NavDrawerItem(
                            id = "home",
                            title = "Home",
                            icon = Icons.Default.Home
                        ),
                        NavDrawerItem(
                            id = "characters",
                            title = "Characters",
                            icon = Icons.Default.People
                        ),
                        NavDrawerItem(
                            id = "comics",
                            title = "Comics",
                            icon = Icons.Default.Book
                        ),
                        NavDrawerItem(
                            id = "events",
                            title = "Events",
                            icon = Icons.Default.CalendarToday
                        ),
                        NavDrawerItem(
                            id = "stories",
                            title = "Stories",
                            icon = Icons.Default.AutoStories
                        ),
                        NavDrawerItem(
                            id = "creators",
                            title = "Creators",
                            icon = Icons.Default.Edit
                        ),
                        NavDrawerItem(
                            id = "favorites",
                            title = "Favorites",
                            icon = Icons.Default.Favorite
                        ),
                        NavDrawerItem(
                            id = "logout",
                            title = "Logout",
                            icon = Icons.AutoMirrored.Filled.ExitToApp
                        ),
                    ),
                    onItemClick = { item ->
                        when (item.id) {
                            "home" -> {
                                navController.navigate("main_screen") {
                                    popUpTo("main_screen") { inclusive = true }
                                }
                            }
                            "favorites" -> {
                                navController.navigate("favorites_screen") {
                                    popUpTo("main_screen") { inclusive = true }
                                }
                            }
                            "characters" -> {
                                navController.navigate("characters_screen") {
                                    popUpTo("main_screen") { inclusive = true }
                                }
                            }
                            "logout" -> {

                            }
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    )
{
        Scaffold(
            topBar = {
                AppBar(
                    title = appBarTitle,
                    onNavigationIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = "main_screen",
                Modifier.padding(contentPadding)
            ) {
                composable("main_screen") {
                    // MainScreenContent()
                }
                composable("characters_screen") {
                    CharactersScreen()
                }
                composable("favorites_screen") {
                    FavoriteScreen()
                }
            }
        }
    }
}






