package com.example.timeinclusionchecker.data

import kotlinx.coroutines.flow.Flow

class OfflineHistoriesRepository(private val HistoryDao: HistoryDao) : HistoriesRepository {
    override fun getAllHistoriesStream(): Flow<List<History>> = HistoryDao.getAllHistorys()

    override fun getHistoriesStream(id: Int): Flow<History?> = HistoryDao.getHistory(id)

    override suspend fun insertHistory(History: History) = HistoryDao.insert(History)

    override suspend fun deleteHistory(History: History) = HistoryDao.delete(History)

    override suspend fun updateHistory(History: History) = HistoryDao.update(History)
}
