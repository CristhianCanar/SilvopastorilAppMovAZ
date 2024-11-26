package com.vanesa.silvopastorilapp.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("jwt") val jwt: String?,
)
