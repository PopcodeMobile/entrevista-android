package com.knowledge.wikisw_luan.data.repository

import retrofit2.http.Headers
import retrofit2.http.POST

interface FavAPI {
    @Headers("prefer: status=400")
    @POST("favorite/{id}")
    suspend fun getWilson()
}