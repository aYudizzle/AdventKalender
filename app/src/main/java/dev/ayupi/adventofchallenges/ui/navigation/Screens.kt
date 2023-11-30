package dev.ayupi.adventofchallenges.ui.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {
    val details: (Int) -> Unit = {
        navController.navigate("details/$it")
    }
}