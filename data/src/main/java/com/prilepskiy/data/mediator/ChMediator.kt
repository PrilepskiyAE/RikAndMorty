package com.prilepskiy.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.prilepskiy.data.database.AppDatabase
import com.prilepskiy.data.database.dao.RemoteKey
import com.prilepskiy.data.database.entity.CharacnedEntity
import com.prilepskiy.data.network.service.RikService
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class ChMediator(
    private val rikApiService: RikService,
    private val dataDB: AppDatabase,
    private val name: String,
    private val status: String,
    private val gender: String,
) : RemoteMediator<Int, CharacnedEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (dataDB.remoteKey.getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacnedEntity>
    ): MediatorResult {

        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = rikApiService.getCharactersFilter(
                name = name,
                status = status,
                gender = gender,
                page = page
            )
            val endOfPaginationReached = apiResponse.info.next.isNullOrEmpty()
            val chEntity = CharacnedEntity.from(apiResponse.result ?: listOf(), page)
            dataDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dataDB.remoteKey.clearRemoteKeys()
                    dataDB.chDao.clearAllCharacters()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = apiResponse.result.map {
                    RemoteKey(
                        chID = it.id,
                        prevKey = prevKey, currentPage = page, nextKey = nextKey
                    )
                }
                dataDB.remoteKey.insert(remoteKeys)
                dataDB.chDao.insert(chEntity)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: Exception) {
            return MediatorResult.Error(error)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacnedEntity>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dataDB.remoteKey.getRemoteKeyByChID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacnedEntity>): RemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { book ->
            dataDB.remoteKey.getRemoteKeyByChID(book.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacnedEntity>): RemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { ch ->
            dataDB.remoteKey.getRemoteKeyByChID(ch.id)
        }
    }

}