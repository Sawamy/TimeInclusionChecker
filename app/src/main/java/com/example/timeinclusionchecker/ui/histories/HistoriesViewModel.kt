package com.example.timeinclusionchecker.ui.histories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timeinclusionchecker.data.HistoriesRepository
import com.example.timeinclusionchecker.data.History
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoriesViewModel(historiesRepository: HistoriesRepository) : ViewModel() {

    val historiesUiState: StateFlow<HistoriesUiState> =
        historiesRepository.getAllHistoriesStream().map { HistoriesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HistoriesUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HistoriesScreen
 */
data class HistoriesUiState(val historiesList: List<History> = listOf())