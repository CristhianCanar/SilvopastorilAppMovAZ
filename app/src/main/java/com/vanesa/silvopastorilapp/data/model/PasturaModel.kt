package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class PasturaModel(
    @SerializedName("id_pastura") val idPastura: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("recuperacion") val recuperacion: Int,
    @SerializedName("tipo_suelo") val tipo_suelo: String,
    @SerializedName("tropico") val tropico: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("url_imagen") val imagen: String,
)
