package com.example.planning.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.planning.data.Notesdata
import com.example.planning.data.Projectdata

@TypeConverters(Converters::class)
@Database(entities = [Projectdata::class,Notesdata::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun notesDao(): NotesDao

}