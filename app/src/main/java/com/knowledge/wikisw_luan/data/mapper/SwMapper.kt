package com.knowledge.wikisw_luan.data.mapper

import com.knowledge.wikisw_luan.data.cache.CharacterEntity
import com.knowledge.wikisw_luan.data.models.CharacterResponse
import com.knowledge.wikisw_luan.models.CharacterModel

object SwMapper {

    fun characterResponseToEntity(response: List<CharacterResponse>, planet: String, specie: String, isFavorite: Boolean): List<CharacterEntity> {
        return response.map { characterResponseToEntity(it, "", "", false) }

    }

    fun characterResponseToEntity(response: CharacterResponse, planet: String, specie: String, isFavorite: Boolean): CharacterEntity {

        val specieId = if (response.species.isEmpty()) {
            ""
        } else {
            response.species[0]
        }

        return CharacterEntity(
            name = response.name,
            mass = response.mass,
            height = response.height,
            hairColor = response.hairColor,
            skinColor = response.skinColor,
            eyeColor = response.eyeColor,
            birthYear = response.birthYear,
            gender = response.gender,
            homeworldId = response.homeworld,
            specieId = specieId,
            homeworld = "",
            specie = "",
            cid = response.name,
            isFavorite = isFavorite

        )

    }

    fun characterEntityToModel(entity: List<CharacterEntity>) : List<CharacterModel> {
       return entity.map { characterEntityToModel(it) }
    }

    fun characterEntityToModel(entity: CharacterEntity) : CharacterModel {
        return CharacterModel(
            name = entity.name,
            mass = entity.mass,
            height = entity.height,
            hairColor = entity.hairColor,
            skinColor = entity.skinColor,
            eyeColor = entity.eyeColor,
            birthYear = entity.birthYear,
            gender = entity.gender,
            homeWorld = entity.homeworld ?: "Planeta não informado",
            homeWorldId = entity.homeworldId.orEmpty(),
            speciesId = entity.specieId.orEmpty(),
            species = entity.specie ?: "Raça não informada",
            isFavorite = entity.isFavorite

        )
    }



}