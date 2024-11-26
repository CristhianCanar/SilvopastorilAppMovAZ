package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class DataResponse<TipoGenerico>(
    @SerializedName("data") val data:List<TipoGenerico>,
    @SerializedName("status") var status:String?,
    @SerializedName("message") var message:String?,
)
