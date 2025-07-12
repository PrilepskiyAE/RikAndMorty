package com.prilepskiy.data.network.service

import com.prilepskiy.data.network.SmartResponse
import com.prilepskiy.data.network.model.CharacnedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RikService {
    @GET("api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): SmartResponse<CharacnedResponse>

    @GET("api/character")
    suspend fun getCharactersFilter(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("type") type: String,
        @Query("genger") gender: String,
        @Query("page") page: Int
    ): SmartResponse<CharacnedResponse>

    @GET("api/character{id}")
    suspend fun getCharacnedDetail(@Path("id") id: Int): CharacnedResponse
}