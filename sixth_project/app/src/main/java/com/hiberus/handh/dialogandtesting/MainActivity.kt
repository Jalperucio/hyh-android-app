package com.hiberus.handh.dialogandtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hiberus.handh.dialogandtesting.ui.theme.DialogAndTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogAndTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var resultDialog by remember {
        mutableStateOf("")
    }
    var isDialogVisible by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name! Selected Action: $resultDialog",
            modifier = modifier
        )
        var spinnerValue by remember {
            mutableStateOf("")
        }
        var spinnerExpanded by remember {
            mutableStateOf(false)
        }



        SpinnerDropDown(
            value = spinnerValue,
            isExpanded = spinnerExpanded,
            label = { /*TODO*/ },
            onExpand = { spinnerExpanded = !spinnerExpanded },
            values = listOf(
                "Opción 1 kjahdfjkasd fjakjf akjf bakjg fakjhf akjhfg akjdfhgaskjfhgka sdhgf kjasjgh",
                "Opción 2",
                "Opción 3"
            )
        ) { selectedValue ->
            spinnerValue = selectedValue
            spinnerExpanded = false
        }

        Button(onClick = { isDialogVisible = true }) {
            Text("Click me!")
        }

        QuestionDialog(
            modifier = Modifier.fillMaxWidth(),
            isVisible = isDialogVisible,
            onAction = { selectedAction ->
                when(selectedAction){
                    "DISMISS" -> resultDialog = "DIALOGO CERRADO!!"
                    "YES" -> resultDialog = "PULSADO SI!!!"
                    "NO" -> resultDialog = "VA A SER QUE NO!!"
                    else -> resultDialog = ""
                }

                isDialogVisible = false
            },
            buttons = mapOf(
                "YES" to @Composable {
                    Text(
                        text = "Va a ser que SI",
                        color = MaterialTheme.colorScheme.onPrimary
                    ) },
                "NO" to @Composable {
                    Text(
                        text = "Va a ser que NO",
                        color = MaterialTheme.colorScheme.onPrimary
                    ) }
            ),
            buttonColors = mapOf(
                "NO" to ButtonDefaults.buttonColors(containerColor = Color.Red)
            )
        ) {
            Text(
                "Qué quereis hacer? quereis hacer más ejercicios de DIalog PORQUE OS ENCANTA?",

            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DialogAndTestingTheme {
        Surface {
            Greeting("Android")
        }
    }
}

@Composable
fun QuestionDialog(
    modifier: Modifier,
    isVisible: Boolean,
    dismissAction: String = "DISMISS",
    dialogProperties: DialogProperties = DialogProperties(),
    onAction: (String) -> Unit,
    buttons: Map<String, @Composable RowScope.() -> Unit> = emptyMap(),
    buttonColors: Map<String, ButtonColors> = emptyMap(),
    content: @Composable () -> Unit
){
    if (isVisible){
        Dialog(
            onDismissRequest = { onAction(dismissAction) },
            properties = dialogProperties,
        ) {
            Card(
                modifier = modifier,
                shape = RoundedCornerShape(24.dp)
            ) {
                ConstraintLayout(
                    modifier = modifier.padding(16.dp)
                ) {
                    val (
                        dialogContainer,
                        buttonsContainer
                    ) = createRefs()

                    Row(modifier = Modifier.constrainAs(buttonsContainer) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                        buttons.forEach { keyValue ->
                            Button(
                                onClick = { onAction(keyValue.key) },
                                colors = if (buttonColors.containsKey(keyValue.key))
                                    buttonColors[keyValue.key]!!
                                else
                                    ButtonDefaults.buttonColors(
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                content = keyValue.value
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }

                    Column(modifier = Modifier.constrainAs(dialogContainer) {
                        bottom.linkTo(buttonsContainer.top)
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }) {
                        content()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>SpinnerDropDown(
    value: T,
    isExpanded: Boolean,
    label: @Composable () -> Unit,
    values: List<T>,
    onExpand: () -> Unit,
    onSelected: (T) -> Unit
){
    var width by remember {
        mutableStateOf(0.dp)
    }
    Box(contentAlignment = Alignment.TopEnd) {
        OutlinedTextField(
            modifier = Modifier.graphicsLayer {
                width = this.size.width.toDp()
            }.clickable { onExpand() },
            value = value.toString(),
            onValueChange = {},
            label = label,
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(if (isExpanded) 180f else 0f)
                        .clickable { onExpand() }
                )


            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onSelected(value) },
            modifier = Modifier.width(width)
        ) {
            values.forEach { value ->
                DropdownMenuItem(text = { Text(value.toString(), softWrap = false, maxLines =  1) }, onClick = { onSelected(value) })
            }
        }
    }
}