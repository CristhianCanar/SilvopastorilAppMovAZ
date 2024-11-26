package com.vanesa.silvopastorilapp.data.dto

import com.google.gson.annotations.SerializedName

data class FincaDTO(
    @SerializedName("user_id") val userId:String,
    @SerializedName("municipio_id") val municipioId:String,
    @SerializedName("pastura_id") val pasturaId:String,
    @SerializedName("ubicacion") val ubicacion:String,
    @SerializedName("nombre") val nombre:String,
    @SerializedName("area_total") val areaTotal:String,
    @SerializedName("area_disponible") val areaDisponible:String,
    @SerializedName("cantidad_ganado") val cantidadGanado:String
)
