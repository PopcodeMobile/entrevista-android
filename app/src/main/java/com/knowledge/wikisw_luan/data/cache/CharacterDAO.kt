package com.knowledge.wikisw_luan.data.cache

import androidx.room.*

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM sw_chars")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM sw_chars where cid == (:id)")
    fun loadCharacterById(id: String): CharacterEntity

    @Query("UPDATE sw_chars SET specie = :specie WHERE specie_id == (:id)")
    fun update(specie: String, id: String)

    @Query("UPDATE sw_chars SET planet = :planet WHERE planet_id == (:id)")
    fun updatePlanet(planet: String, id: String)

    @Query("UPDATE sw_chars SET favorite = :favorite WHERE cid == (:id)")
    fun updateFavorite(favorite: Boolean, id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(a: List<CharacterEntity>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(fav: CharacterEntity)

}