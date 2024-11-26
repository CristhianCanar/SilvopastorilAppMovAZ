package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.network.clients.SignUpApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SignUpService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun signUp(signUp: SignUpDTO):DataResponse<UserModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<UserModel>> = retrofit.create(SignUpApiClient::class.java).signUp(signUp)
            response.body()!!
        }
    }
}