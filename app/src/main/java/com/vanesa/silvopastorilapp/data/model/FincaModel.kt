package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class FincaModel(
    @SerializedName("id_finca") val idFinca: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("municipio_id") val municipioId: String?,
    @SerializedName("pastura_id") val pasturaId: String?,
    @SerializedName("ubicacion") val ubicacion: String?,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("area_total") val areaTotal: String,
    @SerializedName("area_disponible") val areaDisponible: String?,
    @SerializedName("aforo_prom") val aforoProm: String?,
    @SerializedName("num_potreros") val numPotreros: String?,
    @SerializedName("cantidad_ganado") val cantidadGanado: String?,
    @SerializedName("pasturas") val pasturas: PasturaModel?,
    @SerializedName("municipios") val municipios: MunicipioModel?,
)
