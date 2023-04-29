package com.example.mvvm_project.Model

import com.google.gson.annotations.SerializedName

data class ResultApi (
    @SerializedName("results") val poke: List<PokeModel>
)

data class PokeModel(
    @SerializedName("name") val name:String,
    @SerializedName("url") val url: String
)