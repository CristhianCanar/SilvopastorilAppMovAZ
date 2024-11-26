package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.repositories.FincasRepository

class GetFincasByUserIdUseCase {
    private val repository: FincasRepository = FincasRepository()
    suspend operator fun invoke(userId: String):DataResponse<FincaModel>{
        return repository.getFincasByUserId(userId)
    }
}