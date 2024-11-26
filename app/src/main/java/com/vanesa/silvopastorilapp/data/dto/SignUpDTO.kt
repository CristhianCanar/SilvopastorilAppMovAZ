package com.vanesa.silvopastorilapp.data.dto

import com.google.gson.annotations.SerializedName

data class SignUpDTO (
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
)