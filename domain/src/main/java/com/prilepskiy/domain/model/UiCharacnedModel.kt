package com.prilepskiy.domain.model

import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.data.database.entity.CharacnedEntity

data class UiCharacnedModel(
    val id: Int = DEFAULT_INT,
    val name: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val species: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    val nameOrigin: String = EMPTY_STRING,
    val urlOrigin: String = EMPTY_STRING,
    val nameLocation: String = EMPTY_STRING,
    val urlLocation: String = EMPTY_STRING,
    val image: String = EMPTY_STRING,
    val url: String = EMPTY_STRING,
    val created: String = EMPTY_STRING,
    var page: Int = DEFAULT_INT
) {
    companion object {
        fun from(data: CharacnedEntity?): UiCharacnedModel {
            return UiCharacnedModel(
                id = data?.id ?: DEFAULT_INT,
                name = data?.name ?: EMPTY_STRING,
                status = data?.status ?: EMPTY_STRING,
                type = data?.status ?: EMPTY_STRING,
                gender = data?.gender ?: EMPTY_STRING,
                nameOrigin = data?.nameOrigin ?: EMPTY_STRING,
                urlOrigin = data?.urlOrigin ?: EMPTY_STRING,
                nameLocation = data?.nameLocation ?: EMPTY_STRING,
                urlLocation = data?.urlLocation ?: EMPTY_STRING,
                image = data?.image ?: EMPTY_STRING,
                url = data?.url ?: EMPTY_STRING,
                created = data?.created ?: EMPTY_STRING,
                species = data?.species ?: EMPTY_STRING,
                page = data?.page ?: 0
            )
        }

        fun from(data: List<CharacnedEntity>): List<UiCharacnedModel> = data.map { from(it) }

    }
}

