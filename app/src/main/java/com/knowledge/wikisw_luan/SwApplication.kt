package com.knowledge.wikisw_luan

import android.app.Application
import com.knowledge.wikisw_luan.data.cache.CharacterData
import com.knowledge.wikisw_luan.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SwApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CharacterData.initDb(applicationContext)
        startKoin {
            androidLogger()
            androidContext(this@SwApplication)
            modules(arrayListOf(Modules.swModule))
        }
    }
}