package com.github.engelb.stadtlandvollpfosten.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
// import nl.dionsegijn.konfetti.core.models.Size
// import nl.dionsegijn.konfetti.core.models.Shape
// import nl.dionsegijn.konfetti.core.models.Color
import androidx.compose.ui.graphics.Color as ComposeColor
import java.util.concurrent.TimeUnit

@Composable
fun ResultScreen(navController: NavController, correctAnswers: String, totalTime: String) {
    /*
    val message = if (result == 5) "Glückwunsch!" else "Leider nicht geklappt"
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
    }*/

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = if (correctAnswers.toInt() >= 5) "Glückwunsch!" else "Leider nicht geklappt",
                fontSize = 24.sp,
                color = ComposeColor.White
            )
            Text(
                text = "$correctAnswers richtige Antworten in $totalTime Sekunden",
                fontSize = 16.sp,
                color = ComposeColor.White,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = { navController.navigate("rulesScreen") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Zurück zum Menü")
            }
        }

        if (correctAnswers.toInt() >= 5) {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(
                    Party(
                        // Angle - Int (default: 0): The direction the confetti will shoot. Use any integer between 0-360 or use presets like: Angle.TOP, Angle.RIGHT, Angle.BOTTOM, Angle.LEFT. Angle.RIGHT equates to 0 degrees, and larger values move clockwise from this position.
                        angle = 0,
                        // spread - Int (default: 360): How wide the confetti will shoot in the direction of Angle. Use any integer between 0-360. Use 1 to shoot in a straight line and 360 to form a circle
                        spread=360,
                        // speed - Float (default: 30f): The start speed of the confetti at the time of creation.
                        speed = 0f,
                        // maxSpeed - Float (default: 0f): Set to -1 to disable maxSpeed. A random speed between speed and maxSpeed will be chosen. Using randomness creates a more natural and realistic look to the confetti when animating.)
                        maxSpeed = 30f,
                        // damping - Float (default: 0.9f): The rate at which the speed will decrease right after shooting the confetti
                        damping = 0.9f,
                        // size - List<Size> (default: SMALL, MEDIUM, LARGE): The size of the confetti. Use: Size.SMALL, MEDIUM or LARGE for default sizes or create your custom size using a new instance of Size.
                        // colors - List<Int> (default: 0xfce18a, 0xff726d, 0xf4306d, 0xb48def): List of colors that will be randomly picked from
                        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                        // shapes - List<Shape> (default: Shape.Square, Shape.Circle): Or use a custom shape with Shape.DrawableShape
                        // timeToLive - Long (default: 2000): The time in milliseconds a particle will stay alive after that the confetti will disappear.
                        // fadeOutEnabled - Boolean (default: true): If true and a confetti disappears because it ran out of time (set with timeToLive) it will slowly fade out. If set to falls it will instantly disappear from the screen.
                        // position - Position (default: Position.Relative(0.5, 0.5)): the location where the confetti will spawn from relative to the canvas. Use absolute coordinates with [Position.Absolute] or relative coordinates between 0.0 and 1.0 using [Position.Relative]. Spawn confetti between random locations using [Position.between].
                        position = Position.Relative(0.5, 0.3),
                        // delay - Int (default: 0): the amount of milliseconds to wait before the rendering of the confetti starts
                        // rotation - Rotation (default: Rotation): enable the 3D rotation of a Confetti. See [Rotation] class for the configuration options. Easily enable or disable it using [Rotation].enabled() or [Rotation].disabled() and control the speed of rotations.
                        // emitter - EmitterConfig: Instructions how many and how often a confetti particle should spawn. Use Emitter(duration, timeUnit).max(amount) or Emitter(duration, timeUnit).perSecond(amount) to configure the Emitter.
                        emitter = Emitter(duration = 1000, TimeUnit.MILLISECONDS).max(100)
                    )
                )
            )
        }
    }
}
