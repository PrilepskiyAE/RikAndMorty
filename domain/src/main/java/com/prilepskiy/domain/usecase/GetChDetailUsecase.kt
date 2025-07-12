package com.prilepskiy.domain.usecase

import com.prilepskiy.data.repository.ChRepository
import com.prilepskiy.domain.model.UiCharacnedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetChDetailUseCase @Inject constructor(private val repository: ChRepository) {
    operator fun invoke(id: Int): Flow<UiCharacnedModel> =
        repository.getChDetail(id).flowOn(Dispatchers.IO).map { UiCharacnedModel.from(it) }

}