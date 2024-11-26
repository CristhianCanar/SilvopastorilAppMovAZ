package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.network.clients.RotacionesApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class RotacionesService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofit()
    suspend fun postRotacion(rotacionDTO: RotacionDTO):DataResponse<RotacionModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<RotacionModel>> = retrofit.create(RotacionesApiClient::class.java).postRotacion(rotacionDTO)
            response.body()!!
        }
    }


    suspend fun getRotacionesByUserId(userId: String):DataResponse<RotacionModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<RotacionModel>> = retrofit.create(
                RotacionesApiClient::class.java).getRotacionesByUserId(userId)

            response.body()!!
        }
    }
}