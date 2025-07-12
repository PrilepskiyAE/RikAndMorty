package com.prilepskiy.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.prilepskiy.data.database.entity.CharacnedEntity
import com.prilepskiy.data.network.model.CharacnedResponse



@Dao
interface CharacnedDao: BaseDao<CharacnedEntity> {

    @Query("SELECT * FROM characned_table")
    fun getAllCharacters()

    @Query("SELECT * FROM characned_table Order By page")
    fun getAllBookPagingSource(): PagingSource<Int, CharacnedEntity>


    @Query("SELECT * FROM characned_table  WHERE " +
            "name =:name ||" +
            "status=:status ||" +
            "type=:type ||" +
            "gender=:gender Order By page")
    fun getFilterBookPagingSource(name: String,
                                  status: String,
                                  type: String,
                                  gender: String,): PagingSource<Int, CharacnedEntity>

    @Query("SELECT * FROM characned_table WHERE id = :id")
    fun getCharacnedDetail(id: Int): CharacnedResponse

    @Query("Delete From characned_table")
   suspend fun clearAllCharacters()

}