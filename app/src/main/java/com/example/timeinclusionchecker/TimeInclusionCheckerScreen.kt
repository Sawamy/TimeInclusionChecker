package com.example.timeinclusionchecker

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.timeinclusionchecker.ui.StartCheckScreen
import com.example.timeinclusionchecker.ui.histories.HistoriesScreen

enum class TimeInclusionCheckerScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    History(title = R.string.history),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInclusionCheckerBar(
    currentScreen: TimeInclusionCheckerScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun TimeInclusionCheckerApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TimeInclusionCheckerScreen.valueOf(
        backStackEntry?.destination?.route ?: TimeInclusionCheckerScreen.Start.name
    )

    Scaffold(
        topBar = {
            TimeInclusionCheckerBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = TimeInclusionCheckerScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TimeInclusionCheckerScreen.Start.name) {
                StartCheckScreen(
                    onNextButtonClicked = {
                        navController.navigate(TimeInclusionCheckerScreen.History.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = TimeInclusionCheckerScreen.History.name) {
                HistoriesScreen(
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}
