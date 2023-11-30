package dev.ayupi.adventofchallenges.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ayupi.adventofchallenges.R

@Composable
fun DetailsRoute(
    navBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(
        uiState = uiState,
        navBack = navBack
    )
}

@Composable
fun DetailsScreen(uiState: DetailsUiState, navBack: () -> Unit) {
    when (uiState) {
        DetailsUiState.Loading -> Unit
        DetailsUiState.Restricted -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.restricted),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Komm später wieder")
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = navBack) {
                    Text(text = "Zurück zum Kalender")
                }
            }

        }

        is DetailsUiState.Success -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.border_bottom),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.border_top),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Schön das du heute am\n${uiState.day} von 24 Tagen\nvorbei schaust!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(30.dp))

                    Text(
                        text = "Deine heutige Aufgabe",
                        style = MaterialTheme.typography.titleLarge,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = uiState.quest,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = uiState.hint,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = navBack) {
                        Text(text = "Zurück zum Kalender")
                    }
                }
            }
        }
    }
}
