package com.example.mapview

import Model.Place
import Model.StateViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class SystemScreen(val title: String) {
    Start(title = ""),
    Detail(title = ""),
    Map(title = "")
}

var current : Place? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapViewAppBar(
    currentScreen: SystemScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "text"
                    )
                }
            }
        }
    )
}

@Composable
fun MapViewApp(navController: NavHostController = rememberNavController(), currentState:StateViewModel = viewModel()) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = SystemScreen.valueOf(
        backStackEntry?.destination?.route ?: SystemScreen.Start.name
    )

    Scaffold(
        topBar = {
            MapViewAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){innerPadding ->
        val uiState by currentState.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = SystemScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(SystemScreen.Start.name){
                StartScreen(onNextButtonClicked = {
                    currentState.Set(it)
                    navController.navigate(SystemScreen.Detail.name)
                })
            }
            composable(SystemScreen.Detail.name){
                DetailScreen(uiState.currentPlace, onBackButtonClicked = {
                    navController.navigate(SystemScreen.Start.name)
                },onNextButtonClicked = {
                    navController.navigate(SystemScreen.Map.name)
                })
            }
            composable(SystemScreen.Map.name){
                MapScreen(uiState.currentPlace, onBackButtonClicked = {
                    navController.navigate(SystemScreen.Detail.name)
                })
            }
        }
    }
}

