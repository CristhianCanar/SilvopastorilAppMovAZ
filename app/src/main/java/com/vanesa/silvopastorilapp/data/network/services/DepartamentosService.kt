package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import com.vanesa.silvopastorilapp.data.network.clients.DepartamentosApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class DepartamentosService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofit()
    suspend fun getDepartamentos():DataResponse<DepartamentoModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<DepartamentoModel>> = retrofit.create(DepartamentosApiClient::class.java).getDepartamentos()
            response.body()!!
        }
    }
}