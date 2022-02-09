package com.example.todayproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todayproject.dao.TodoDao
import com.example.todayproject.dto.Todo

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}