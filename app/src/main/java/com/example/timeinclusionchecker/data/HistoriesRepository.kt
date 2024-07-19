package com.example.timeinclusionchecker.data

import kotlinx.coroutines.flow.Flow

interface HistoriesRepository {
        /**
         * Retrieve all the Histories from the the given data source.
         */
        fun getAllHistoriesStream(): Flow<List<History>>

        /**
         * Retrieve an History from the given data source that matches with the [id].
         */
        fun getHistoriesStream(id: Int): Flow<History?>

        /**
         * Insert History in the data source
         */
        suspend fun insertHistory(history: History)

        /**
         * Delete History from the data source
         */
        suspend fun deleteHistory(history: History)

        /**
         * Update History in the data source
         */
        suspend fun updateHistory(history: History)

}