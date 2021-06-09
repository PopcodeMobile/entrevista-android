package com.knowledge.wikisw_luan.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sw_chars")
data class CharacterEntity(

    @PrimaryKey val cid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "mass") val mass: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "hair_color") val hairColor: String,
    @ColumnInfo(name = "skin_color") val skinColor: String,
    @ColumnInfo(name = "eye_color") val eyeColor: String,
    @ColumnInfo(name = "birth_year") val birthYear: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "specie") var specie: String?,
    @ColumnInfo(name = "specie_id") var specieId: String?,
    @ColumnInfo(name = "planet") var homeworld: String?,
    @ColumnInfo(name = "planet_id") var homeworldId: String?,
    @ColumnInfo(name = "favorite") var isFavorite: Boolean
)