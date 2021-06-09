package com.knowledge.wikisw_luan.adapter

import com.knowledge.wikisw_luan.models.CharacterModel

interface ClickWikiListener {

    fun onListClick(character: CharacterModel)

    fun onFavClick(character: CharacterModel)

}