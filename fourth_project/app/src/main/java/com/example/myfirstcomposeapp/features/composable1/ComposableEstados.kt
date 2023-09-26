package com.example.myfirstcomposeapp.features.composable1

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState") @Composable
fun MyComposable() {
    // Este no funciona correctamente, ya que ante cualquier cambio de estado, se inicializa durante la recomposición
    val state = mutableStateOf(0)
    // Este funciona, pero no es intuitivo, se modifica el estado a través de la propiedad value
    val state2 = remember { mutableStateOf(0) }
    // Este es el ideal, funciona correctgamente, y es intuitivo (se maneja con el tipo genérico del estado)
    var state3 by remember { mutableStateOf(0) }
    // Este también funciona perfectamente, además permite guardado durante cambios en la actividad (por ejemplo, rotaciones)
    var state4 by rememberSaveable { mutableStateOf(0) }
    // Este también funciona (value es el valor de acceso y setvValue es el valor de modificación) este tipo de definición se llama deconstrucción.
    var (value, setValue) = remember { mutableStateOf(0) }

    // Ejemplo de transición infinita de cambio de color mediante animación básica.
    val transition = rememberInfiniteTransition("InfiniteColor")
    val colorBack by transition.animateColor(initialValue = Color.Red,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorBlack",

    )
    Column(
        modifier = Modifier.fillMaxSize().background(colorBack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "¡Hola, soy el número ${state3}!")
        Spacer(modifier = Modifier.padding(15.dp))
        Button(onClick = {
            state3 += 1
        }){
            Text(text = "Botón")
        } }
}
@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true) @Composable
fun PreviewMyComposable() {
    MyComposable()
}