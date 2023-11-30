package dev.ayupi.adventofchallenges.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.ayupi.adventofchallenges.ui.navigation.destinations.challengesComposable
import dev.ayupi.adventofchallenges.ui.navigation.destinations.detailsComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = START_DESTINATION
            ) {
                challengesComposable(navigateToDetails = {
                    screen.details(it)
                })
                detailsComposable(navBack = { navController.navigateUp() })
            }
        }
    }
}

const val START_DESTINATION = "challenges"