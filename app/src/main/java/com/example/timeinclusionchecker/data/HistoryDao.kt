package com.example.timeinclusionchecker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(History: History)

    @Update
    suspend fun update(History: History)

    @Delete
    suspend fun delete(History: History)

    @Query("SELECT * from History WHERE id = :id")
    fun getHistory(id: Int): Flow<History>

    @Query("SELECT * from History ORDER BY id ASC")
    fun getAllHistorys(): Flow<List<History>>
}