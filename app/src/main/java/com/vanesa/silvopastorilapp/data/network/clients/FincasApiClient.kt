package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FincasApiClient {
    @POST("fincas")
    suspend fun postFinca(@Body fincaDTO: FincaDTO): Response<DataResponse<String>>

    @POST("fincas/{fincaId}")
    suspend fun updateFinca(@Path("fincaId") fincaId: String, @Body fincaDTO: FincaDTO): Response<DataResponse<String>>

    @GET("fincas/{userId}")
    suspend fun getFincasByUserId(@Path("userId") userId: String): Response<DataResponse<FincaModel>>
}