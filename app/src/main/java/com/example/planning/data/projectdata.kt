package com.example.planning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class projectdata(
    @PrimaryKey
    val name: String,
    val Priority: String,
    val progressbar: Int,
    val time : List<String>
)