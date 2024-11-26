package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.network.services.LoginService
import com.vanesa.silvopastorilapp.data.providers.UserProvider

class LoginRepository {
    private val api = LoginService()
    suspend fun signIn(loginDTO: LoginDTO):DataResponse<UserModel>{
        val response:DataResponse<UserModel> = api.signIn(loginDTO)
        if (response.status == "success"){
            if(response.data.isNotEmpty()){
                UserProvider.userModel = response.data[0]
            }
        }
        return response
    }
}