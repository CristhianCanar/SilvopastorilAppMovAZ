package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiClient {
    @POST("signup")
    suspend fun signUp(@Body signUpDTO: SignUpDTO): Response<DataResponse<UserModel>>
}