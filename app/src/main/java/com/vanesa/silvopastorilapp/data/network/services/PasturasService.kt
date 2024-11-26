package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.data.network.clients.PasturasApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class PasturasService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofit()
    suspend fun getPasturasPartial():DataResponse<PasturaPartialModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<PasturaPartialModel>> = retrofit.create(PasturasApiClient::class.java).getPasturasPartial()
            response.body()!!
        }
    }

    // Obtener pasturas
    suspend fun getPasturas():DataResponse<PasturaModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<PasturaModel>> = retrofit.create(PasturasApiClient::class.java).getPasturas()
            response.body()!!
        }
    }
}