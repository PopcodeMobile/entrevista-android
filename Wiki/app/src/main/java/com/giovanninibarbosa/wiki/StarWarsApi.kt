package com.giovanninibarbosa.wiki

import com.google.gson.GsonBuilder

import io.reactivex.rxjava3.core.Observable

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StarWarsApi {

    val service: StarWarsApiDef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        service = retrofit.create<StarWarsApiDef>(StarWarsApiDef::class.java)
    }

    fun loadPeople(): Observable<People> {
        return service.listPeople()
            .flatMap { peopleResult -> Observable.fromIterable(peopleResult.results) }
            .map { people ->
                People(people.name, people.height, people.gender, people.mass)
            }

    }

}