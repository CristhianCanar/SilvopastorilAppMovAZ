package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import com.vanesa.silvopastorilapp.data.repositories.MunicipiosRepository

class GetMunicipiosByDepartamentoIdUseCase {
    private val repository: MunicipiosRepository = MunicipiosRepository()
    suspend operator fun invoke(departamentoId: String):DataResponse<MunicipioModel>{
        return repository.getMunicipiosByDepartamentoId(departamentoId)
    }
}