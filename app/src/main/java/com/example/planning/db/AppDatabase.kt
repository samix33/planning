package com.example.planning.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planning.data.projectdata

@Database(entities = [projectdata::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao

}