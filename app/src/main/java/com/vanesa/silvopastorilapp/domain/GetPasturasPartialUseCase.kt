package com.vanesa.silvopastorilapp.domain

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.data.repositories.PasturasRepository

class GetPasturasPartialUseCase {
    private val repository: PasturasRepository = PasturasRepository()
    suspend operator fun invoke():DataResponse<PasturaPartialModel>{
        return repository.getPasturasPartial()
    }
}