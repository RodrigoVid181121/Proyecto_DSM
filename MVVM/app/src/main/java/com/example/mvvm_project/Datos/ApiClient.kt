package com.example.mvvm_project.Datos

import com.example.mvvm_project.Model.PokeModelDetails
import com.example.mvvm_project.Model.ResultApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET(value = "pokemon?limit=1154")
    suspend fun getPokemonList(): Response<ResultApi>

    @GET(value = "/pokemon/{id}")
    suspend fun getPokeDetails(@Path("id") id : Int): Response<PokeModelDetails>
}