package com.prilepskiy.data.database.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ch_id")
    val chID: Int,
    @ColumnInfo(name = "prevKey")
    val prevKey: Int?,
    @ColumnInfo(name = "currentPage")
    val currentPage: Int,
    @ColumnInfo(name = "nextKey")
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)