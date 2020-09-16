package com.giovanninibarbosa.wiki

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import java.util.*

interface StarWarsApiDef {
    @GET("people/")
    fun listPeople() : Observable<PeopleResult>
}