package com.example.todayproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todayproject.dto.Menu
import com.example.todayproject.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {
    val menuList: LiveData<MutableList<Menu>>
    private val menuRepository: MenuRepository = MenuRepository.get()

    init {
        menuList = menuRepository.list()
    }

    fun getOne(time: String) = menuRepository.getMenu(time)

    fun insert(dto: Menu) = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.insert(dto)
    }

    fun update(dto: Menu) = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.update(dto)
    }

}