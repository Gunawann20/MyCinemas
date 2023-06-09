package com.example.mycinemas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycinemas.ui.navigation.Screen
import com.example.mycinemas.ui.screen.detail.DetailScreen
import com.example.mycinemas.ui.screen.home.HomeScreen
import com.example.mycinemas.ui.screen.profile.ProfileScreen

@Composable
fun MyCinemas(
    navController: NavHostController = rememberNavController()
){

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route){
            HomeScreen(
                navigateToDetail = { movieId ->
                    navController.navigate(Screen.DetailMovie.createRoute(movieId))
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable(Screen.Profile.route){
            ProfileScreen(navigateBack = {
                navController.navigateUp()
            })
        }
        composable(
            route = Screen.DetailMovie.route,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType})
        ){
            val id = it.arguments?.getLong("movieId") ?: -1L
            DetailScreen(
                movieId = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}