package com.knowledge.wikisw_luan.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun charDao(): CharacterDAO
}