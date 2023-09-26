package com.example.myfirstcomposeapp

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.previews.MySuperPreview
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@MySuperPreview
@Composable
fun Greeting(
    @PreviewParameter(MiSuperParameterProvider::class)
    name: String,
    modifier: Modifier = Modifier
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

        Text(
            text = "Hello $tex!",
            modifier = modifier
        )
        Box {

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                onClick = { tex += " ADIOS COMPOSE" }
            ) {
                Text("Púlsame 2")
            }

            Button(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .clip(Heart()),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = { tex += " HOOOOOLA COMPOSE" }) {
                Text("Púlsame")
            }
        }
    }

}

@MySuperPreview
@Composable
fun GreetingPreview() {
    MyFirstComposeAppTheme {
        Surface {
            Greeting("Android")
        }
    }
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