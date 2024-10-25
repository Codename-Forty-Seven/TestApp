package com.example.test.task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.task.presentation.model.FactUi

@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(factUi: FactUi)

    @Query("SELECT * FROM facts_table ORDER BY id DESC")
    suspend fun getAllFacts(): List<FactUi>
}