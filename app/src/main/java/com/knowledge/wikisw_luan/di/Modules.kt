package com.knowledge.wikisw_luan.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.knowledge.wikisw_luan.activity.CharViewModel
import com.knowledge.wikisw_luan.activity.MainViewModel
import com.knowledge.wikisw_luan.data.SwAPI
import com.knowledge.wikisw_luan.data.SwCloud
import com.knowledge.wikisw_luan.data.cache.CharacterData
import com.knowledge.wikisw_luan.data.cache.CharacterDatabase
import com.knowledge.wikisw_luan.data.repository.FavAPI
import com.knowledge.wikisw_luan.data.repository.SwRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Modules {
    val swModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(OkHttpClient.Builder().build())
                .build()
                .create(SwAPI::class.java)
        }
        single {
            Retrofit.Builder()
                .baseUrl("http://private-782d3-starwarsfavorites.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(OkHttpClient.Builder().build())
                .build()
                .create(FavAPI::class.java)
        }
        single { SwCloud(get()) }
        single { SwRepository(get(), CharacterData.db.charDao()) }
        viewModel { MainViewModel(get()) }
        viewModel { CharViewModel(get()) }
    }
}