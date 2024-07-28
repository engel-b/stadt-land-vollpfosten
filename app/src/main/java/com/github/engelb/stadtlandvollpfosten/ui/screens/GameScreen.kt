package com.github.engelb.stadtlandvollpfosten.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.engelb.stadtlandvollpfosten.ui.components.SubcomposeRow
import com.github.engelb.stadtlandvollpfosten.ui.theme.ContinueBg
import com.github.engelb.stadtlandvollpfosten.ui.theme.ContinueFg
import com.github.engelb.stadtlandvollpfosten.ui.theme.OkBg
import com.github.engelb.stadtlandvollpfosten.ui.theme.OkFg
import kotlinx.coroutines.delay

val categories = listOf(
    "Automarke",
    "Automodell",
    "Baumarktartikel",
    "Bekleidungsmarke",
    "Berg",
    "Beruf",
    "Blume",
    "Buch",
    "Bücherregal-Kategorie",
    "Chemisches Element",
    "Comicfigur",
    "Ding, das in einen Koffer passt",
    "Fabelwesen",
    "Farbe",
    "Fiktiver Charakter",
    "Film",
    "Fluss",
    "Gefühl/Emotion",
    "Gericht (Essen)",
    "Geschmacksrichtung",
    "Gesellschaftsspiel",
    "Getränk",
    "Gott/Göttin",
    "Haarfarbe",
    "Historische Figur",
    "Hobby",
    "Insekt",
    "Jungenname",
    "Kartenspiel",
    "Kleidungsstück",
    "Körperteil",
    "Krankheit",
    "Küchengerät",
    "Küchenkräuter",
    "Kündigungsgrund",
    "Künstler",
    "Land",
    "Lebensmittelmarke",
    "Mädchenname",
    "Märchenfigur",
    "Märchenland",
    "Medizinisches Instrument",
    "Möbelstück",
    "Modeaccessoire",
    "Modekette",
    "Mordwerkzeug",
    "Musikalbum",
    "Musikband",
    "Musikinstrument",
    "Obst oder Gemüse",
    "Pflanze",
    "Pizzabelag",
    "Raum im Haus",
    "Reiseziele",
    "Schimpfwort",
    "Schulfach",
    "Spielzeug",
    "Spionagegerät",
    "Sportart",
    "Sprichwort",
    "Stadt",
    "Sternbild",
    "Superheld",
    "Süßigkeit",
    "Technisches Gerät",
    "Tee-Sorte",
    "Tier",
    "TV-Sendung",
    "Unternehmen",
    "Unterwasserlebewesen",
    "Verbrechen",
    "Vorname",
    "Werkzeug",
    "Zauberspruch",
    "Zaubertrank-Zutat",
    "Zeitung",
    "Handwerk",
    "Kinofilm"
)

//@Preview(showSystemUi = true)
@Composable
fun GameScreen(navController: NavController) {
    var letter by remember { mutableStateOf(generateRandomLetter()) }
    var timeLeft by remember { mutableStateOf(30) }
    var correctAnswers by remember { mutableStateOf(0) }
    val timerRunning = remember { mutableStateOf(true) }
    val chosenCategories =
        remember { mutableStateListOf<String>() } // MutableStateList für gewählte Kategorien
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
                        if (timeLeft > 0 && correctAnswers < 5) {
                            pickRandomCategory()
                        }
                    }) {
                    Text("Weiter", fontSize = 40.sp, color = ContinueFg)
                }

                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(containerColor = OkBg),
                    onClick = {
                        correctAnswers++
                        if (timeLeft > 0 && correctAnswers < 5) {
                            pickRandomCategory()
                        }
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

