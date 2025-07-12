package com.prilepskiy.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prilepskiy.common.emitFlow
import com.prilepskiy.data.database.AppDatabase
import com.prilepskiy.data.database.entity.CharacnedEntity
import com.prilepskiy.data.mediator.ChMediator
import com.prilepskiy.data.network.service.RikService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChRepository @Inject constructor(
    private val dataNetwork: RikService,
    private val dataDB: AppDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getFilterCh(
        name: String,
        status: String,
        gender: String,
    ): Flow<PagingData<CharacnedEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                dataDB.chDao.getFilterChPagingSource(name)
            },
            remoteMediator = ChMediator(dataNetwork, dataDB, name, status, gender)
        ).flow
    }

    fun getChDetail(id: Int): Flow<CharacnedEntity?> = emitFlow {
        val cache = dataDB.chDao.getCharacnedDetail(id)
        if (cache!= null) cache else {
            dataNetwork.getCharacnedDetail(id)
            CharacnedEntity.from(dataNetwork.getCharacnedDetail(id), 1)
        }
    }

}

