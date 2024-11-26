package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.data.repositories.SignUpRepository

class PostSignUpUseCase {
    private val repository = SignUpRepository()
    suspend operator fun invoke(signUpDTO: SignUpDTO):DataResponse<UserModel>{
        return repository.signUp(signUpDTO)
    }
}
