package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import com.vanesa.silvopastorilapp.data.repositories.DepartamentosRepository

class GetDepartamentosUseCase {
    private val repository: DepartamentosRepository = DepartamentosRepository()
    suspend operator fun invoke():DataResponse<DepartamentoModel>{
        return repository.getDepartamentos()
    }
}