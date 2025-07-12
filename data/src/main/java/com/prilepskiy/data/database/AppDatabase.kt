package com.prilepskiy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.common.VERSION_DATABASE
import com.prilepskiy.data.database.dao.CharacnedDao
import com.prilepskiy.data.database.dao.RemoteKey
import com.prilepskiy.data.database.dao.RemoteKeyDao
import com.prilepskiy.data.database.entity.CharacnedEntity

@Database(
    entities = [CharacnedEntity::class, RemoteKey::class],
    version = VERSION_DATABASE
)
abstract class AppDatabase : RoomDatabase() {
    abstract val chDao: CharacnedDao
    abstract val remoteKey: RemoteKeyDao
}