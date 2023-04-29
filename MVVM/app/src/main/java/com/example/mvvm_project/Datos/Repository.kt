package com.example.mvvm_project.Datos

import com.example.mvvm_project.Model.ItemPoke
import com.example.mvvm_project.Model.PokeItemDetails
import com.example.mvvm_project.Model.toDomain
import com.example.mvvm_project.Datos.ApiService

class Repository {

    private val _api = ApiService()

    suspend fun AllPokemons() : List<ItemPoke>{
        val response = _api.PokeGets()

        return response.map{it.toDomain()}
    }

    suspend fun getPokeDetails(id: Int): PokeItemDetails?{
        val response = _api.getPokeDetails(id)
        return response?.toDomain()
    }
}