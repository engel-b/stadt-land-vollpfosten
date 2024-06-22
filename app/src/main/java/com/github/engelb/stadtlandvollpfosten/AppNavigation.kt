package com.github.engelb.stadtlandvollpfosten

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.engelb.stadtlandvollpfosten.ui.screens.GameScreen
import com.github.engelb.stadtlandvollpfosten.ui.screens.ModeScreen
import com.github.engelb.stadtlandvollpfosten.ui.screens.ResultScreen
import com.github.engelb.stadtlandvollpfosten.ui.screens.RulesScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "modeScreen") {
        composable("modeScreen") { ModeScreen(navController) }
        composable("rulesScreen") { RulesScreen(navController) }
        composable("gameScreen") { GameScreen(navController) }
        composable("resultScreen/{result}/{time}") { backStackEntry ->
            val result = backStackEntry.arguments?.getString("result") ?: "0"
            val time = backStackEntry.arguments?.getString("time") ?: "0"
            ResultScreen(navController, result, time)
        }
    }
}
