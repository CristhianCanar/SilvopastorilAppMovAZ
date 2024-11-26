package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import com.vanesa.silvopastorilapp.data.network.clients.MunicipiosApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class MunicipiosService {
    val retrofit: Retrofit = RetrofitHelper.getRetrofit()
    suspend fun getMunicipiosByDepartamentoId(departamentoId: String):DataResponse<MunicipioModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<MunicipioModel>> = retrofit.create(MunicipiosApiClient::class.java).getMunicipiosByDepartamentoId(departamentoId)
            response.body()!!
        }
    }
}