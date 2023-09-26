package com.example.myfirstcomposeapp.containers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.R
import kotlinx.coroutines.launch

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
fun EjemploScaffoldMaterial3(@PreviewParameter(BooleanPreviewParameter::class) isLazy: Boolean){
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var snackBarResult: SnackbarResult by remember { mutableStateOf(SnackbarResult.Dismissed) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isFloatingActionButtonVisible by remember { mutableStateOf(true) }
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(
                connection = scrollBehavior.nestedScrollConnection,
            ),
    topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            scrollBehavior = scrollBehavior
        )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
        AnimatedVisibility(
            visible = isFloatingActionButtonVisible,
            enter = scaleIn(
                tween(
                    durationMillis = 800,
                    easing = LinearOutSlowInEasing
                )
            ),
            exit = scaleOut(
                tween(
                    durationMillis = 800,
                    easing = LinearOutSlowInEasing
                )
            )
             
        ) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    snackBarResult = snackBarHostState.showSnackbar(
                        message = "Prueba de ejecución de Snackbar",
                        actionLabel = "Ejecutar",
                        withDismissAction = true,
                        duration = SnackbarDuration.Long,
                    )
                }
            }
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
    },
    bottomBar = {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) {
            NavigationBarItem(
                selected = true,
                onClick = {
                    coroutineScope.launch {
                         
                    }
                },
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null)
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            )
        }
    },
    snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        if (snackBarResult == SnackbarResult.ActionPerformed){
            Text(text = "Snackbar Ejecutada")
        }
        if (!isLazy)
            NoLazyColum(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                items = IntRange(1,100).toList()
            ) {
                Text("Item nro. $it")
            }
        else
            LazyColumExample(
                items = IntRange(1,100).toList(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)){
                MyCardConstraint(
                    title = "Mi $it Título",
                    name = "Mi Nombre es el $it",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
    }
     
    isFloatingActionButtonVisible = scrollBehavior.state.collapsedFraction < 0.5f
}


@Composable
private fun NoLazyColum(items: List<Int>, modifier: Modifier = Modifier, item: @Composable (Int) -> Unit){
    Column(
        modifier = modifier,
    ) {
        items.forEach {
            item(it)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyColumExample(items: List<Int>, modifier: Modifier = Modifier, item: @Composable (Int) -> Unit){
    LazyColumn(modifier = modifier) {
        val groups = items.groupBy{id ->
            id / 10
        }
        groups.forEach { (key, value) ->
            stickyHeader {
                Text(
                    text = "${key * 10} Sticky", modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth(),
                    color = Color.White)
            }
            items(value) {
                item(it)
            }
        }

    }
}

class BooleanPreviewParameter: PreviewParameterProvider<Boolean>{
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)

}