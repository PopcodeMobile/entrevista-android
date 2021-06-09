package com.knowledge.wikisw_luan.data.cache

import android.content.Context
import androidx.room.Room

object CharacterData {
    lateinit var db: CharacterDatabase
    fun initDb(applicationContext: Context) {
        db = Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java, "sw-db"
        ).allowMainThreadQueries().build()
    }
}