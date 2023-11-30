package dev.ayupi.adventofchallenges.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ayupi.adventofchallenges.models.Challenges
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val day = savedStateHandle.getStateFlow(key = "day", -1)
    val uiState: StateFlow<DetailsUiState> = day.map {
        val isToday = dateCheck(it)
        if(isToday) {
            val dailyInfos = Challenges.entries[it-1]
            DetailsUiState.Success(
                day = "$it.",
                quest = dailyInfos.quest,
                hint = dailyInfos.hint
            )
        } else {
            DetailsUiState.Restricted
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailsUiState.Loading
    )
}

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data object Restricted : DetailsUiState
    data class Success(
        val day: String,
        val quest: String,
        val hint: String,
    ) : DetailsUiState
}

fun dateCheck(day: Int, month: Int = 12): Boolean {
    val currentDate = Calendar.getInstance()
    val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
    val currentMonth = currentDate.get(Calendar.MONTH) + 1 // Monate sind 0-basiert, daher +1 (0-11 Januar -> 0...)
    return currentDay >= day && currentMonth == month
}