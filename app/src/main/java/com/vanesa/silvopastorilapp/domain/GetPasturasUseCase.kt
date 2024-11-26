package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.data.repositories.PasturasRepository

class GetPasturasUseCase {
    private val repository: PasturasRepository = PasturasRepository()
    suspend operator fun invoke():DataResponse<PasturaModel>{
        return repository.getPasturas()
    }
}