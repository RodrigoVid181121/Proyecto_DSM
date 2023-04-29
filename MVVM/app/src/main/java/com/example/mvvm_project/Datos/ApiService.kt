package com.example.mvvm_project.Datos

import com.example.mvvm_project.Model.PokeModel
import com.example.mvvm_project.Model.PokeModelDetails
import com.example.mvvm_project.Nucleo.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    private val _retroFit = RetrofitHelper.getRetro()

    suspend fun PokeGets(): List<PokeModel>{
        return withContext(Dispatchers.IO){
            val response = _retroFit.create(ApiClient::class.java).getPokemonList()
            response.body()?.poke ?: emptyList()
        }
    }

    suspend fun getPokeDetails(id: Int): PokeModelDetails?{
        return withContext(Dispatchers.IO){
            val response = _retroFit.create(ApiClient::class.java).getPokeDetails(id)
            response.body()
        }
    }

}