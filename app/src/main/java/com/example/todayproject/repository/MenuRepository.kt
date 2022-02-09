package com.example.todayproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todayproject.database.MenuDatabase
import com.example.todayproject.dto.Menu

private const val DATABASE_NAME = "menu-database.db"
class MenuRepository private constructor(context: Context){
    private val database: MenuDatabase = Room.databaseBuilder(
        context.applicationContext,
        MenuDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val menuDao = database.menuDao()

    fun list(): LiveData<MutableList<Menu>> = menuDao.list()

    fun getMenu(time: String): Menu = menuDao.selectOne(time)

    fun insert(dto: Menu) = menuDao.insert(dto)

    suspend fun update(dto: Menu) = menuDao.update(dto)

    companion object {
        private var INSTANCE: MenuRepository?=null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MenuRepository(context)
            }
        }

        fun get(): MenuRepository {
            return INSTANCE ?:
            throw IllegalStateException("MenuRepository must be initialized")
        }
    }
}