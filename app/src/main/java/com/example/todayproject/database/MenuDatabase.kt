package com.example.todayproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todayproject.dao.MenuDao
import com.example.todayproject.dto.Menu

@Database(entities = arrayOf(Menu::class), version = 1)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}