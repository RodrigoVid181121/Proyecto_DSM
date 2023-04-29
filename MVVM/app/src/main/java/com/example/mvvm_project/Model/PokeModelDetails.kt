package com.example.mvvm_project.Model

import com.google.gson.annotations.SerializedName

data class PokeModelDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val details: List<Stats>,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("weight") val weight: Int,
)

data class Sprites(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val artwork: Artwork
)

data class Artwork(
    @SerializedName("front_default") val img: String
)

data class Stats(
    @SerializedName("base_stat") val statValue: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val statName: String
)

data class Types(
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)