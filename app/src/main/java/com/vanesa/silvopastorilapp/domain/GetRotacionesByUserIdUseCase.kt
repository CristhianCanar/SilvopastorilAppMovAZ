package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.repositories.RotacionesRepository

class GetRotacionesByUserIdUseCase {
    private val repository: RotacionesRepository = RotacionesRepository()
    suspend operator fun invoke(userId: String):DataResponse<RotacionModel>{
        return repository.getRotacionesByUserId(userId)
    }
}