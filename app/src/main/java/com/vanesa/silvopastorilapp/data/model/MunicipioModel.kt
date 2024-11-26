package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class MunicipioModel(
    @SerializedName("id_municipio") val idMunicipio: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("tipo_tropico") val tipoTropico: String,
    @SerializedName("departamento_id") val departamentoId: String,
    @SerializedName("departamentos") val departamentos: DepartamentoModel?,
)
