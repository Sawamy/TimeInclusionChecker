package com.example.timeinclusionchecker.data

data class CheckUiState(

    /** 開始時間 */
    val startTime: String = "選択してください",
    /** 終了時間 */
    val lastTime: String = "選択してください",
    /** ターゲット時間 */
    val targetTime: String = "選択してください",
    /** ターゲットが指定範囲に含まれるかチェックした結果 */
    val isInRange: String = "",

    )
