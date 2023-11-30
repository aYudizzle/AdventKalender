package dev.ayupi.adventofchallenges.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ayupi.adventofchallenges.ui.details.DetailsRoute

fun NavGraphBuilder.detailsComposable(navBack: () -> Unit) {
    composable(
        route = "details/{day}",
        arguments = listOf(
            navArgument(name = ARGUMENT_DETAILS_KEY){
                type = NavType.IntType
            },
        )
    ) {
        DetailsRoute(navBack = navBack)
    }
}

const val ARGUMENT_DETAILS_KEY = "day"