package com.example.timeinclusionchecker.data

import kotlinx.coroutines.flow.Flow

interface HistoriesRepository {
        /**
         * すべての History を取得する
         */
        fun getAllHistoriesStream(): Flow<List<History>>

        /**
         * [id] に一致する History を取得する
         */
        fun getHistoriesStream(id: Int): Flow<History?>

        /**
         * History を挿入する
         */
        suspend fun insertHistory(history: History)

        /**
         * History を削除する
         */
        suspend fun deleteHistory(history: History)

        /**
         *  History の更新
         */
        suspend fun updateHistory(history: History)

}