package com.knowledge.wikisw_luan.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val name: String,
    val mass: String,
    val height: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeWorld: String,
    val homeWorldId: String,
    val species: String,
    val speciesId: String,
    var isFavorite: Boolean = false
) : Parcelable
