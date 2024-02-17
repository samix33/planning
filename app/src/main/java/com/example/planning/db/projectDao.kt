package com.example.planning.db

import androidx.room.*
import com.example.planning.data.projectdata

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(projectDao: List<projectdata>)

    @Query("SELECT * FROM project_table")
    suspend fun getAll(): List<projectdata>

    @Delete
    suspend fun delethestory(projectDao : projectdata )
}