package com.knowledge.wikisw_luan.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val name: String,
    val mass: String,
    val height: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("birth_year") val birthYear: String,
    val gender: String,
    val homeworld: String,
    val species: List<String>
)
