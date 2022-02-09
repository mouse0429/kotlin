package com.example.todayproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todayproject.database.TodoDatabase
import com.example.todayproject.dto.Todo

private const val DATABASE_NAME = "todo-database.db"
class TodoRepository private constructor(context: Context){
    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val todoDao = database.todoDao()

    fun list(): LiveData<MutableList<Todo>> = todoDao.list()

    fun getTodo(id: Long): Todo = todoDao.selectOne(id)

    fun insert(dto: Todo) = todoDao.insert(dto)

    suspend fun update(dto: Todo) = todoDao.update(dto)

    companion object {
        private var INSTANCE: TodoRepository?=null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository {
            return INSTANCE ?:
            throw IllegalStateException("TodoRepository must be initialized")
        }
    }

}