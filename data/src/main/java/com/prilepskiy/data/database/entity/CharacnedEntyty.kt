package com.prilepskiy.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prilepskiy.common.DEFAULT_INT
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.data.network.model.CharacnedResponse

@Entity("characned_table")
data class CharacnedEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "name_origin")
    val nameOrigin: String,
    @ColumnInfo(name = "url_origin")
    val urlOrigin: String,
    @ColumnInfo(name = "name_location")
    val nameLocation: String,
    @ColumnInfo(name = "url_Location")
    val urlLocation: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "created")
    val created: String,
    @ColumnInfo(name = "page")
    var page: Int
) {
    companion object {
        fun from(data: CharacnedResponse, page: Int): CharacnedEntity {
            return CharacnedEntity(
                id = data.id ?: DEFAULT_INT,
                name = data.name ?: EMPTY_STRING,
                status = data.status ?: EMPTY_STRING,
                type = data.status ?: EMPTY_STRING,
                gender = data.gender ?: EMPTY_STRING,
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
