package com.example.mvvm_project.Model

import com.example.mvvm_project.Model.PokeModel
import java.util.*
data class ItemPoke(
    val id: Int,
    val name: String,
    val img: String
){
    val idFormat = "N° ${id.toString().padStart(3,'0')}"
}

private const val ART_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

fun PokeModel.toDomain(): ItemPoke{
    val uriArray = url.split("/")
    val id = uriArray[uriArray.size - 2].toInt()
    val name = name.replaceFirstChar {name ->
        if(name.isLowerCase()){
            name.titlecase(Locale.getDefault())
        }
        else{
            name.toString()
        }
    }
    val img = "$ART_URL$id.png"

    return ItemPoke(id,name,img)
}