package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import retrofit2.Response
import retrofit2.http.GET

interface DepartamentosApiClient {
    @GET("departamentos")
    suspend fun getDepartamentos(): Response<DataResponse<DepartamentoModel>>
}