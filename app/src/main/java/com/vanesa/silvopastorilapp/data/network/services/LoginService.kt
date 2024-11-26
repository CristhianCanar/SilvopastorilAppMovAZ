package com.vanesa.silvopastorilapp.data.network.services

import com.vanesa.silvopastorilapp.core.RetrofitHelper
import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.network.clients.LoginApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun signIn(loginDTO: LoginDTO):DataResponse<UserModel>{
        return withContext(Dispatchers.IO){
            val response: Response<DataResponse<UserModel>> = retrofit.create(LoginApiClient::class.java).signIn(loginDTO)
            response.body()!!
        }
    }
}