package com.prilepskiy.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(data: List<T>)

    @Update
    suspend fun update(data: T)

    @Delete
     suspend fun delete(data: T)

}