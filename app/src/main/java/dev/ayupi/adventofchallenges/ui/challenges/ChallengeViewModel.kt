package dev.ayupi.adventofchallenges.ui.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor() : ViewModel() {
    private val day = markedDay()
    val uiState : StateFlow<ChallengeUiState> = day.map {
        ChallengeUiState.Success(
            currentDay = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChallengeUiState.Loading
    )
}

sealed interface ChallengeUiState {
    data object Loading : ChallengeUiState
    data class Success(
        val currentDay: Int
    ) : ChallengeUiState
}

fun markedDay(month: Int = 12): Flow<Int> {
    val currentDate = Calendar.getInstance()
    val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
    val currentMonth = currentDate.get(Calendar.MONTH) + 1 // Monate sind 0-basiert, daher +1 (0-11 Januar -> 0...)
    if(currentMonth == month) {
        return flow { emit(currentDay) }
    }
    return flow { emit(-1) }
}