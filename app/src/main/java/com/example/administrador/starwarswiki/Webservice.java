package com.example.administrador.starwarswiki;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("id") annotation on the id parameter marks it as a
     * replacement for the {id} placeholder in the @GET path
     */
    @GET("api/people/{id}")
    Call<StarWarsCharacter> getStarWarsCharacter(@Path("id") int id);

    @GET("api/people")
    Call<PeopleList> getStarWarsCharacters();

    @GET("api/people/")
    Call<PeopleList> getStarWarsCharacters(@Query("page") int pageNum);

    @GET("api/species/{id}")
    Call<Specie> getSpecies(@Path("id") int id);

    @GET("api/planets/{id}")
    Call<Planet> getPlanet(@Path("id") int id);

}

