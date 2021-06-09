package com.knowledge.wikisw_luan.data.models

data class SwResponse(
    val results: List<CharacterResponse>,
    val next: String?
)
