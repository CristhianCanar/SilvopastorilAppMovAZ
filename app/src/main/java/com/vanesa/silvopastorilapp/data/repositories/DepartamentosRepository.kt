package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import com.vanesa.silvopastorilapp.data.network.services.DepartamentosService
import com.vanesa.silvopastorilapp.data.providers.DepartamentosProvider

class DepartamentosRepository {
    val departamentosService = DepartamentosService()

    suspend fun getDepartamentos():DataResponse<DepartamentoModel>{
        val response:DataResponse<DepartamentoModel> = departamentosService.getDepartamentos()
        if (response.status == "success"){
            if (response.data != null){
                DepartamentosProvider.departamentosModel = response.data
            }
        }
        return response
    }
}