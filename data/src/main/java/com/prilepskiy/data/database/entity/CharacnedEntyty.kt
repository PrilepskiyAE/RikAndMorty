package com.prilepskiy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.data.network.model.CharacnedResponse

@Entity("characned_table")
data class CharacnedEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val nameOrigin: String,
    val urlOrigin: String,
    val nameLocation: String,
    val urlLocation: String,
    val image: String,
    val url: String,
    val created: String,
    var page: Int
) {
    companion object {
        fun from(data: CharacnedResponse?, page: Int): CharacnedEntity {
            return CharacnedEntity(
                id = data?.id ?: DEFAULT_INT,
                name = data?.name ?: EMPTY_STRING,
                status = data?.status ?: EMPTY_STRING,
                type = data?.status ?: EMPTY_STRING,
                gender = data?.gender ?: EMPTY_STRING,
                nameOrigin = data?.origin?.name ?: EMPTY_STRING,
                urlOrigin = data?.origin?.url ?: EMPTY_STRING,
                nameLocation = data?.location?.name ?: EMPTY_STRING,
                urlLocation = data?.location?.url ?: EMPTY_STRING,
                image = data?.image ?: EMPTY_STRING,
                url = data?.url ?: EMPTY_STRING,
                created = data?.created ?: EMPTY_STRING,
                species = data?.species ?: EMPTY_STRING,
                page = page,

                )
        }
        fun from(data: List<CharacnedResponse>, page: Int): List<CharacnedEntity> = data.map { from(it,page) }

    }
}
