package com.example.planning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_table")
data class Notesdata(
    @PrimaryKey
    val title: String,
    val detail: String,
    
)