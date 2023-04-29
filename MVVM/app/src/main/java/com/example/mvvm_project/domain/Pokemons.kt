package com.example.mvvm_project.domain

import com.example.mvvm_project.Datos.Repository
import com.example.mvvm_project.Model.ItemPoke

class Pokemons {

    private val _repository = Repository()
    //regresa todos los pokemons que hay en el json
    suspend fun AllofIt(): List<ItemPoke>{
        return _repository.AllPokemons()
    }
}