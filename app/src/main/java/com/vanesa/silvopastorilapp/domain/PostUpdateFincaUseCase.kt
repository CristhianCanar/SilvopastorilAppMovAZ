package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.repositories.FincasRepository

class PostUpdateFincaUseCase {
    private val repository: FincasRepository = FincasRepository()
    suspend operator fun invoke(fincaId: String, fincaDTO: FincaDTO):DataResponse<String>{
        return repository.updateFinca(fincaId, fincaDTO)
    }
}