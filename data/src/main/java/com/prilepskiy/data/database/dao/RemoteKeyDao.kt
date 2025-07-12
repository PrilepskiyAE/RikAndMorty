package com.prilepskiy.data.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RemoteKeyDao: BaseDao<RemoteKey> {

    @Query("Select * From remote_key Where ch_id == :id")
    abstract suspend fun getRemoteKeyByChID(id: Int): RemoteKey?

    @Query("Delete From remote_key")
    abstract  suspend fun clearRemoteKeys()

    @Query("Select created_at From remote_key Order By created_at DESC LIMIT 1")
    abstract  suspend fun getCreationTime(): Long?
}