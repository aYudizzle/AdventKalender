package dev.ayupi.adventofchallenges.ui.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.ayupi.adventofchallenges.ui.challenges.ChallengeRoute

fun NavGraphBuilder.challengesComposable(navigateToDetails: (Int) -> Unit) {
    composable(
        route = CHALLENGE_ROUTE
    ) {
       ChallengeRoute(navigateToDetails = navigateToDetails)
    }
}

const val CHALLENGE_ROUTE = "challenges"