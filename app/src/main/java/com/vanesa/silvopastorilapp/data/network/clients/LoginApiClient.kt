package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiClient {
    @POST("sign_in")
    suspend fun signIn(@Body loginDTO: LoginDTO): Response<DataResponse<UserModel>>
}