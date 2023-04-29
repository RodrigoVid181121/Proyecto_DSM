package com.example.mvvm_project.domain

import com.example.mvvm_project.Datos.Repository
import com.example.mvvm_project.Model.PokeItemDetails

class Details {

    private val _repository = Repository()
    //Trae todos los detalles del pokemon mediante el id otorgado
    suspend fun fromPoke(id: Int): PokeItemDetails? {
        return _repository.getPokeDetails(id)
    }
}