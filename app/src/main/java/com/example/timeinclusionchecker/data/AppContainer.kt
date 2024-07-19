package com.example.timeinclusionchecker.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val historiesRepository: HistoriesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineHistoriesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val historiesRepository: HistoriesRepository by lazy {
        OfflineHistoriesRepository(TimeInclusionCheckerDatabase.getDatabase(context).historyDao())
    }
}