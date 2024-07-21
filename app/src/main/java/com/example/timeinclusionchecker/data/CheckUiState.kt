package com.example.timeinclusionchecker.data

data class CheckUiState(

    /** 開始時刻 */
    val startTime: String = "選択してください",
    /** 終了時刻 */
    val lastTime: String = "選択してください",
    /** ターゲット時刻 */
    val targetTime: String = "選択してください",
    /** ターゲットが指定範囲に含まれるかチェックした結果 */
    val isInRange: String = "",

    )
