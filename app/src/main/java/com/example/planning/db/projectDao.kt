package com.example.planning.db

import androidx.room.*
import com.example.planning.data.Projectdata

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(projectDao: List<Projectdata>)

    @Query("SELECT * FROM project_table")
    suspend fun getAll(): List<Projectdata>

    @Delete
    suspend fun deletProject(projectDao : Projectdata)
}