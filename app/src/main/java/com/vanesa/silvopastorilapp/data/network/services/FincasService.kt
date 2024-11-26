package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.network.clients.FincasApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class FincasService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofit()
    suspend fun postFinca(fincaDTO: FincaDTO):DataResponse<String>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<String>> = retrofit.create(FincasApiClient::class.java).postFinca(fincaDTO)
            response.body()!!
        }
    }
    suspend fun updateFinca(fincaId:String, fincaDTO: FincaDTO):DataResponse<String>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<String>> = retrofit.create(FincasApiClient::class.java).updateFinca(fincaId, fincaDTO)
            response.body()!!
        }
    }
    suspend fun getFincasByUserId(userId: String):DataResponse<FincaModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<FincaModel>> = retrofit.create(FincasApiClient::class.java).getFincasByUserId(userId)
            response.body()!!
        }
    }
}