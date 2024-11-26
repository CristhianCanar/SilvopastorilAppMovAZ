package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class DepartamentoModel(
    @SerializedName("id_departamento") val idDepartamento: String,
    @SerializedName("nombre") val nombre: String,
)
