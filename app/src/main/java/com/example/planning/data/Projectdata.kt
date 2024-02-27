package com.example.planning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Projectdata(
    @PrimaryKey
    val name: String,
    val detail: String,
    val Priority: String,
    val progressbar: Int,
    val timeStart : String,
    val timeEnd : String
)