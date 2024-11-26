package com.vanesa.silvopastorilapp.data.dto

import com.google.gson.annotations.SerializedName

data class RotacionDTO(
    @SerializedName("finca_id") val fincaId:String,
    @SerializedName("periodo_ocupacion") val periodoOcupacion:String,
    @SerializedName("peso_animal_prom") val pesoAnimalProm:String,
    @SerializedName("aforo") val aforo:String,
    @SerializedName("numero_potreros") val numeroPotreros:String,
    @SerializedName("area_cada_potrero") val areaCadaPotrero:String,
    @SerializedName("forraje_total") val forrajeTotal:String,
    @SerializedName("forraje_disponible") val forrajeDisponible:String,
    @SerializedName("total_animales") val totalAnimales:String,
)
