package br.com.jaysonsabino.desafioandroidpopcode.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.jaysonsabino.desafioandroidpopcode.entities.Character;

@Dao
public interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Character> characters);

    @Query("DELETE FROM Character")
    void deleteAll();

    @Query("SELECT * FROM Character ORDER BY created")
    DataSource.Factory<Integer, Character> findAll();

    @Query("SELECT * FROM Character WHERE name LIKE :name ORDER BY created")
    DataSource.Factory<Integer, Character> findByName(String name);

    @Query("SELECT * FROM Character WHERE EXISTS (select 1 from FavoriteCharacter WHERE characterId = id) ORDER BY created")
    DataSource.Factory<Integer, Character> findAllFavorites();

    @Query("SELECT * FROM Character WHERE name LIKE :name AND EXISTS (select 1 from FavoriteCharacter WHERE characterId = id) ORDER BY created")
    DataSource.Factory<Integer, Character> findAllFavoritesByName(String name);
}
