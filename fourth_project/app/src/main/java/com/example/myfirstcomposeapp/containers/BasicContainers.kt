package com.example.myfirstcomposeapp.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview
@Composable
fun BoxExample(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        // Por defecto, al centro (por contentAlignment)
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Red)
        )
        // Los siguientes nosotros decidimos
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Blue)
            .align(Alignment.TopCenter)
        )
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Yellow)
            .align(Alignment.TopStart)
        )
    }
}

@Preview
@Composable
fun ColumnExample(){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Red)
        )
        // Los siguientes nosotros decidimos
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Blue)
        )
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Yellow)
        )
    }
}
@Preview
@Composable
fun ColumnExample2(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Red)
        )
        // Los siguientes nosotros decidimos
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Blue)
        )
        Box(modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.Yellow)
        )
    }
}

@Preview
@Composable
fun ColumnExample3(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        IntRange(1,20).forEach {
            MyCard(title = "Mi Título $it", name = "Mi Nombre $it", modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth())
        }
    }
}


@Preview
@Composable
fun ColumnExample4(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        IntRange(1,20).forEach {
            MyCardConstraint(title = "Mi Título $it", name = "Mi Nombre $it", modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth())
        }
    }
}

@Composable
private fun MyCard(title: String, name: String, modifier: Modifier = Modifier){
    Card(modifier = modifier) {
        Row {
            Box(modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.Red)
            )
            Column {
                Text(text = title)
                Text(text = name)
            }
        }
    }
}

@Composable
fun MyCardConstraint(title: String, name: String, modifier: Modifier = Modifier){
    Card(modifier = modifier) {
        ConstraintLayout(modifier = modifier){
            val (
                pictureView,
                titleView,
                nameView
            ) = createRefs()
            val topGuide = createGuidelineFromTop(0.1f)
            val bottomGuide = createGuidelineFromBottom(0.1f)
            val startGuidePercent = createGuidelineFromStart(10.dp)
            Box(modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.Red).constrainAs(pictureView){
                    top.linkTo(topGuide)
                    bottom.linkTo(bottomGuide)
                    start.linkTo(startGuidePercent)
                }
            )

            Text(text = title,
                modifier = Modifier.constrainAs(titleView){
                    top.linkTo(parent.top)
                    start.linkTo(pictureView.end, margin = 3.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    bottom.linkTo(nameView.top)
                }
            )
            Text(
                text = name,
                modifier = Modifier.constrainAs(nameView){
                    top.linkTo(titleView.bottom, margin = 3.dp)
                    start.linkTo(titleView.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            createVerticalChain(titleView, nameView, chainStyle = ChainStyle.SpreadInside)
        }
    }
}