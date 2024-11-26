package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.repositories.RotacionesRepository

class PostRotacionUseCase {
    private val repository: RotacionesRepository = RotacionesRepository()
    suspend operator fun invoke(rotacionDTO: RotacionDTO):DataResponse<RotacionModel>{
        return repository.postRotacion(rotacionDTO)
    }
}