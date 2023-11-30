package dev.ayupi.adventofchallenges.ui.challenges

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ayupi.adventofchallenges.models.Challenges

@Composable
fun ChallengeRoute(
    navigateToDetails: (Int) -> Unit,
    viewModel: ChallengeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ChallengeScreen(
        uiState = uiState,
        navigateToDetails = navigateToDetails
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChallengeScreen(uiState: ChallengeUiState, navigateToDetails: (Int) -> Unit) {
    when (uiState) {
        ChallengeUiState.Loading -> Unit
        is ChallengeUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Willkommen beim \nAdvent of Challenges",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.Center
                ) {
                    (1..24).forEach {
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .padding(4.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            val isActive = uiState.currentDay >= it
                            if (isActive) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp),
                                    painter = painterResource(id = Challenges.entries[it - 1].imageResId),
                                    contentDescription = null
                                )
                            }
                            TextButton(onClick = { navigateToDetails(it) }) {
                                Text(
                                    text = if (isActive) "" else "$it",
                                    style = TextStyle(
                                        color = if (isActive) Color.White else Color.Black,
                                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 24.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}