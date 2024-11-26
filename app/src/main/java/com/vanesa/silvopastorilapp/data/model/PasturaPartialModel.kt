package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class PasturaPartialModel(
    @SerializedName("id_pastura") val idPastura: String,
    @SerializedName("nombre") val nombre: String,
)
