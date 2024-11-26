package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.network.services.SignUpService
import com.vanesa.silvopastorilapp.data.providers.UserProvider

class SignUpRepository {
    private val api = SignUpService()
    suspend fun signUp(signUpDTO: SignUpDTO):DataResponse<UserModel>{
        val response:DataResponse<UserModel> = api.signUp(signUpDTO)
        if (response.status == "success"){
            if(response.data.isNotEmpty()){
                UserProvider.userModel = response.data[0]
            }
        }
        return response
    }
}