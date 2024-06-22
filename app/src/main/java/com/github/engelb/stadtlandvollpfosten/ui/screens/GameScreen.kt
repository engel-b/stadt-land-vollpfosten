package com.github.engelb.stadtlandvollpfosten.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.engelb.stadtlandvollpfosten.ui.components.SubcomposeRow
import com.github.engelb.stadtlandvollpfosten.ui.theme.ContinueBg
import com.github.engelb.stadtlandvollpfosten.ui.theme.ContinueFg
import com.github.engelb.stadtlandvollpfosten.ui.theme.OkBg
import com.github.engelb.stadtlandvollpfosten.ui.theme.OkFg

val categories = listOf(
    "Stadt",
            "Land",
            "Fluss",
            "Tier",
            "Pflanze",
            "Beruf",
            "Film",
            "Musikband",
            "Automarke",
            "Süßigkeit",
            "Getränk",
            "Märchenfigur",
            "Superheld",
            "Schimpfwort",
            "Küchengerät",
            "Krankheit",
            "Chemisches Element",
            "Fiktiver Charakter",
            "Gesellschaftsspiel",
            "Sportart",
    "Farbe",
            "Kleidungsstück",
            "Körperteil",
            "Instrument",
            "Werkzeug",
            "Pizzabelag",
            "Geschmacksrichtung",
            "Obst oder Gemüse",
            "Vorname",
            "Mädchenname",
            "Beruf",
            "Mordwerkzeug",
            "Baumarktartikel",
            "Modekette"
)

//@Preview(showSystemUi = true)
@Composable
fun GameScreen(navController: NavController) {
    var letter by remember { mutableStateOf(generateRandomLetter()) }
    var timeLeft by remember { mutableStateOf(30) }
    var correctAnswers by remember { mutableStateOf(0) }
    val timerRunning = remember { mutableStateOf(true) }
    val chosenCategories = remember { mutableStateListOf<String>() } // MutableStateList für gewählte Kategorien
    var currentCategory by remember { mutableStateOf<String?>(null) } // MutableState für die aktuelle Kategorie

    // Funktion zum Auswählen einer zufälligen Kategorie
    fun pickRandomCategory() {
        val availableCategories = categories - chosenCategories
        if (availableCategories.isNotEmpty()) {
            val randomCategory = availableCategories.random()
            chosenCategories.add(randomCategory)
            currentCategory = randomCategory
        } else {
            currentCategory = "Keine Kategorien mehr verfügbar"
            timerRunning.value = false
            navController.navigate("resultScreen/$correctAnswers/${30 - timeLeft}")
        }
    }

    LaunchedEffect(key1 = timerRunning.value) {
        pickRandomCategory()
        while (timerRunning.value && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        if (timeLeft == 0) {
            timerRunning.value = false
            navController.navigate("resultScreen/$correctAnswers/${30 - timeLeft}")
        }
    }

    if (correctAnswers == 5) {
        timerRunning.value = false
        navController.navigate("resultScreen/$correctAnswers/${30 - timeLeft}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Buchstabe:", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "$letter", fontSize = 40.sp)

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Kategorie:")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "${currentCategory}", fontSize = 40.sp)

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Verbleibende Zeit: $timeLeft Sekunden")

        Spacer(modifier = Modifier.height(60.dp))
        Row {
            SubcomposeRow(
                paddingBetween = 20.dp
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = ContinueBg),
                    onClick = {
                        pickRandomCategory()
                }) {
                    Text("Weiter", fontSize = 40.sp, color = ContinueFg)
                }

                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(containerColor = OkBg),
                    onClick = {
                        correctAnswers++
                        pickRandomCategory()
                    }
                ) {
                    Text("✔", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = OkFg)
                }

            }
        }
    }
}

fun generateRandomLetter(): Char {
    val letters = ('A'..'Z').filter { it != 'X' && it != 'Y' }
    return letters.random()
}
