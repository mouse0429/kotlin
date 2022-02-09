package com.example.todayproject.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="menuTable")
class Menu (
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "time") @PrimaryKey(autoGenerate = false) val time: String
)