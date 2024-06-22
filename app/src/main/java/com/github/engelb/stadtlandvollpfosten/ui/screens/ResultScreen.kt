package com.github.engelb.stadtlandvollpfosten.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, result: String, time: String) {
    val message = if (result.toInt() == 5) "Glückwunsch!" else "Leider nicht geklappt"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$message $result richtige Antworten in $time Sekunden", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("rulesScreen") }) {
            Text(text = "Zurück zu den Regeln")
        }
    }
}
