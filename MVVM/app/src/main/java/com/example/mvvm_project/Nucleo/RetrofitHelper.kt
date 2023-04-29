package com.example.mvvm_project.Nucleo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val URL = "https://pokeapi.co/api/v2/"

    fun getRetro(): Retrofit{
        return Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}