package com.example.todayproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todayproject.dto.Menu

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dto: Menu)

    @Query("select * from menuTable")
    fun list(): LiveData<MutableList<Menu>>

    @Query("select * from menuTable where time = (:time)")
    fun selectOne(time: String): Menu

    @Update
    suspend fun update(dto: Menu)
}