package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.repositories.FincasRepository

class PostFincaUseCase {
    private val repository: FincasRepository = FincasRepository()
    suspend operator fun invoke(fincaDTO: FincaDTO):DataResponse<String>{
        return repository.postFinca(fincaDTO)
    }
}