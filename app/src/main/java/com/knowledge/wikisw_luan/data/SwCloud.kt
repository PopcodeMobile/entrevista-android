package com.knowledge.wikisw_luan.data

import com.knowledge.wikisw_luan.data.models.BasicResponse
import com.knowledge.wikisw_luan.data.models.SwResponse

class SwCloud(
    private val api: SwAPI
) {
    suspend fun getCharacters(page: Int): SwResponse {
        return api.getCharacters(page)
    }

    suspend fun getSpecies(id: Int): BasicResponse {
        return api.getSpecies(id)
    }

    suspend fun getPlanet(id: Int): BasicResponse {
        return api.getPlanet(id)
    }

}