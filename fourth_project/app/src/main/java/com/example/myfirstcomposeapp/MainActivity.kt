package com.example.myfirstcomposeapp

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstcomposeapp.features.composable1.PasswordTextField
import com.example.myfirstcomposeapp.features.composable1.RadioButtonGroup
import com.example.myfirstcomposeapp.previews.MySuperPreview
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyFirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ){
                        composable("home"){
                            Greeting("Android"){
                                navController.navigate("detail")
                            }
                        }
                        composable("detail"){
                            Greeting("Androideeeee DETALLE") {
                                navController.navigate("home")
                            }
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column {
        var tex by remember {
            mutableStateOf(name)
        }

        Box(
            modifier = Modifier
                //.padding(16.dp)
                //.background(Color.Red)
                //.padding(16.dp)
                //.background(Color.Green)
                //.padding(16.dp)
                //.background(Color.Blue)
                //.padding(16.dp)
                .clip(Heart())

                .size(200.dp)
                .background(Color.Red)
                .clickable { }
        )
        var tx by rememberSaveable {
            mutableStateOf("")
        }
        Text(
            text = "Hello $tex! NroChars: ${tx.length}",
            modifier = modifier,
            style = MaterialTheme.typography.bodyLarge,
        )

        OutlinedTextField(
            value = tx,
            onValueChange = { newText ->
                tx = newText
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Mi Primer TextField") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(

            )
        )
        var password by remember {
            mutableStateOf("")
        }
        PasswordTextField(
            labelText = "Contraseña",
            password = password,
            onPasswordChange = { pwd -> password = pwd }
        )

        var rbSelected by remember {
            mutableIntStateOf(0)
        }

        Row (modifier = Modifier.clickable { rbSelected = 0 }){
            RadioButton(selected = rbSelected == 0, onClick = { rbSelected = 0 })
            Text(text = "Pulsame 1")
        }

        Row (modifier = Modifier.clickable { rbSelected = 1 }){
            RadioButton(selected = rbSelected == 1, onClick = { rbSelected = 1 })
            Text(text = "Pulsame 2")
        }

        var selectedValue by remember {
            mutableStateOf(MisPosiblesValores.None)
        }

        RadioButtonGroup(
            borderStroke = BorderStroke(1.dp, Color.Black),
            radioButtonValues = MisPosiblesValores.values(),
            selectedValue = selectedValue,
            onCheckedChanged = { newValue ->
                selectedValue = newValue
            },
            radioButtonLabel = { value ->
                Text(value.text)
            },
            excludedValues = arrayOf(MisPosiblesValores.None)
        )

        Box {
            Button(
                modifier = Modifier
                    .fillMaxSize(0.5f),
                    //.clip(Heart()),
                shape = Heart(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                onClick()
                }) {
                Text("Púlsame")
            }
        }
    }
}

enum class MisPosiblesValores(val text: String) {
    None(""),
    ValorA("Valor A"),
    ValorB("Valor B"),
    ValorC("Valor C")

}

class MiSuperParameterProvider: PreviewParameterProvider<String>{
    override val values: Sequence<String>
        get() = sequenceOf(
            "Hello",
            "Hi",
            "Hola",
            "Bonjour"
        )
}

class Heart: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            heartPath(size = size)
            close()
        }
        return Outline.Generic(path)
    }
}

fun Path.heartPath(size: Size): Path {
    //the logic is taken from StackOverFlow [answer](https://stackoverflow.com/a/41251829/5348665)and converted into extension function

    val width: Float = size.width
    val height: Float = size.height

    // Starting point
    moveTo(width / 2, height / 5)

    // Upper left path
    cubicTo(
        5 * width / 14, 0f,
        0f, height / 15,
        width / 28, 2 * height / 5
    )

    // Lower left path
    cubicTo(
        width / 14, 2 * height / 3,
        3 * width / 7, 5 * height / 6,
        width / 2, height
    )

    // Lower right path
    cubicTo(
        4 * width / 7, 5 * height / 6,
        13 * width / 14, 2 * height / 3,
        27 * width / 28, 2 * height / 5
    )

    // Upper right path
    cubicTo(
        width, height / 15,
        9 * width / 14, 0f,
        width / 2, height / 5
    )
    return this
}