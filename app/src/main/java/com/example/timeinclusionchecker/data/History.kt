package com.example.timeinclusionchecker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    /** 確認時間 */
    val checkTime: String,
    /** 開始時間 */
    val startTime: String,
    /** 終了時間 */
    val lastTime: String,
    /** ターゲット時間 */
    val targetTime: String,
    /** ターゲットが指定範囲に含まれるかチェックした結果 */
    val isInRange: String = "",
)
