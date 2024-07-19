package com.example.timeinclusionchecker.ui

import androidx.lifecycle.ViewModel
import com.example.timeinclusionchecker.data.CheckUiState
import com.example.timeinclusionchecker.data.HistoriesRepository
import com.example.timeinclusionchecker.data.History
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CheckViewModel(private val historiesRepository: HistoriesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckUiState())
    val uiState: StateFlow<CheckUiState> = _uiState.asStateFlow()

    /**
     * 範囲開始時間を更新する
     */
    fun updateStartTime(startTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                startTime = startTime
            )
        }
    }

    /**
     * 範囲終了時間を更新する
     */
    fun updateLastTime(lastTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                lastTime = lastTime
            )
        }
    }

    /**
     * ターゲット時間を更新する
     */
    fun updateTargetTime(targetTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                targetTime = targetTime
            )
        }
    }

    /**
     * ターゲットの時間が指定した範囲の時間に含まれているか確認する
     */
    fun checkInRange() {

        val startTime = _uiState.value.startTime
        val lastTime = _uiState.value.lastTime
        val targetTime = _uiState.value.targetTime

        if (startTime != "選択してください"
            && lastTime != "選択してください"
            && targetTime != "選択してください"
        ) {
            val startTimeInt = startTime.toInt()
            val lastTimeInt = lastTime.toInt()
            val targetTimeInt = targetTime.toInt()

            var result = false

            if (startTimeInt <= lastTimeInt) {
                // 範囲が同じ日の場合
                if (targetTimeInt in startTimeInt..lastTimeInt) {
                    result = true
                }
            } else {
                // 範囲が日をまたぐ場合
                if (targetTimeInt in startTimeInt..23 || targetTimeInt in 0..lastTimeInt)
                    result = true
            }

            if (result) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isInRange = "範囲内"
                    )
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        isInRange = "範囲外"
                    )
                }
            }


        } else {
            // 未入力があったら何もしない
        }
    }

    suspend fun saveHistory() {
        val temp = HistoryDetails(
            0,
            startTime = _uiState.value.startTime,
            lastTime = _uiState.value.lastTime,
            targetTime = _uiState.value.targetTime,
            isInRange = _uiState.value.isInRange
        )
        historiesRepository.insertHistory(temp.toHistory())
    }
}

data class HistoryDetails(
    val id: Int = 0,
    val startTime: String = "",
    val lastTime: String = "",
    val targetTime: String = "",
    val isInRange: String = "",
)

fun HistoryDetails.toHistory(): History = History(
    id = id,
    startTime = startTime,
    lastTime = lastTime,
    targetTime = targetTime,
    isInRange = isInRange
)