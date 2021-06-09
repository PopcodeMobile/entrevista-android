package com.knowledge.wikisw_luan.activity

import com.knowledge.wikisw_luan.models.CharacterModel

sealed class SwState {
    class ShowCharacters(val list: List<CharacterModel>) : SwState()
    class ShowSpecieName(val specieName: String) : SwState()
    class ShowPlanetName(val planetName: String) : SwState()

}
