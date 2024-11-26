package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RotacionesApiClient {
    @POST("rotaciones")
    suspend fun postRotacion(@Body rotacionDTO: RotacionDTO): Response<DataResponse<RotacionModel>>

    @GET("rotaciones/{userId}")
    suspend fun getRotacionesByUserId(@Path("userId") userId: String): Response<DataResponse<RotacionModel>>

}