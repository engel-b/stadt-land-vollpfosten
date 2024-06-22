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
import androidx.navigation.NavController

@Composable
fun RulesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Spielregeln:")
        Text("1. Ein Buchstabe A-Z wird zufällig ausgewählt (außer X und Y).")
        Text("2. Der Spieler hat 30 Sekunden, um 5 Kategorien zu beantworten.")
        Text("3. Fällt keine Antwort ein, kann 'Weiter' gedrückt werden.")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("gameScreen") }) {
            Text(text = "Start")
        }
    }
}
