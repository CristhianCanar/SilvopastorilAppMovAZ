package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MunicipiosApiClient {
    @GET("municipios/{departamentoId}")
    suspend fun getMunicipiosByDepartamentoId(@Path("departamentoId") departamentoId: String): Response<DataResponse<MunicipioModel>>
}