package com.example.todayproject.config

import android.app.Application
import com.example.todayproject.repository.MenuRepository
import com.example.todayproject.repository.TodoRepository

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()

        TodoRepository.initialize(this)
        MenuRepository.initialize(this)
    }
}