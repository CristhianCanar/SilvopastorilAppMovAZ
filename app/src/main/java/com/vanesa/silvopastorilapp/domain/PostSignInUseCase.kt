package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.repositories.LoginRepository

class PostSignInUseCase {
    private val repository = LoginRepository()
    suspend operator fun invoke(loginDTO: LoginDTO):DataResponse<UserModel>{
        return repository.signIn(loginDTO)
    }
}
