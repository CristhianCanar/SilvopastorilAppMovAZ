package com.vanesa.silvopastorilapp.data.network.clients

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import retrofit2.Response
import retrofit2.http.GET

interface PasturasApiClient {
    @GET("pasturas/names")
    suspend fun getPasturasPartial(): Response<DataResponse<PasturaPartialModel>>

    // Todas los datos de las pasturas
    @GET("pasturas")
    suspend fun getPasturas(): Response<DataResponse<PasturaModel>>
}