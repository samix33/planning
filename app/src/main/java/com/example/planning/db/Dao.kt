package com.example.planning.db

import androidx.room.*
import com.example.planning.data.Notesdata
import com.example.planning.data.Projectdata

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(projectDao: List<Projectdata>)

    @Query("SELECT * FROM project_table")
    suspend fun getAll(): List<Projectdata>

    @Delete
    suspend fun deletProject(projectDao : Projectdata)

    @Query("DELETE FROM project_table")
    suspend fun deleteAll()
}
@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(Notes: List<Notesdata>)

    @Query("SELECT * FROM Notes_table")
    suspend fun getAll(): List<Notesdata>

    @Delete
    suspend fun deletProject(projectDao : Notesdata)

    @Query("DELETE FROM Notes_table")
    suspend fun deleteAll()
}