package com.prilepskiy.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.prilepskiy.data.repository.ChRepository
import com.prilepskiy.domain.model.UiCharacnedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFilterChUseCase @Inject constructor(private val repository: ChRepository) {
    operator fun invoke(
        name: String,
        status: String,
        gender: String,
    ): Flow<PagingData<UiCharacnedModel>> =
        repository.getFilterCh(name, status, gender).flowOn(
            Dispatchers.IO
        ).map { pg ->
            pg.map {
                UiCharacnedModel.from(it)
            }.filter {
                if (gender.isNotEmpty()) {
                    it.gender == gender
                } else true
            }.filter {
                if (status.isNotEmpty()) {
                    it.status == status
                } else true
            }
        }

}