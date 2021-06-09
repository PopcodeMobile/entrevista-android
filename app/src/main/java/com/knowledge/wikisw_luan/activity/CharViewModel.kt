package com.knowledge.wikisw_luan.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knowledge.wikisw_luan.data.repository.SwRepository
import kotlinx.coroutines.launch

class CharViewModel(private val repository: SwRepository) : ViewModel() {
    private val _state: MutableLiveData<Any> = MutableLiveData()
    val state: MutableLiveData<Any> get() = _state
    fun getPlanets(planetId: String) {
        viewModelScope.launch {
            try {
                val planet = repository.getPlanets(planetId)
                _state.value = SwState.ShowPlanetName(planet)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSpecies(speciesId: String) {
        viewModelScope.launch {
            try {
                val specieName = repository.getSpecies(speciesId)
                _state.value = SwState.ShowSpecieName(specieName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFav(charId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.getFav(charId, isFavorite)
        }
    }
}