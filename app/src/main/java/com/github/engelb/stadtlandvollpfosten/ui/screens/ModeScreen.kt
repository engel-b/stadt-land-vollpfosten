package com.github.engelb.stadtlandvollpfosten.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.github.engelb.stadtlandvollpfosten.R

@Composable
fun ModeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Hintergrundbild
        Image(
            painter = painterResource(id = R.drawable.background), // Hintergrundbild
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f) // Setzt die Transparenz des Bildes
        )

        Button(onClick = { navController.navigate("rulesScreen") }) {
            Text(text = "Energy")
        }
    }
}
