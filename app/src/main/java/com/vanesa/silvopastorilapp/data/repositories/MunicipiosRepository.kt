package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import com.vanesa.silvopastorilapp.data.network.services.MunicipiosService
import com.vanesa.silvopastorilapp.data.providers.MunicipiosProvider

class MunicipiosRepository {
    val municipiosService = MunicipiosService()

    suspend fun getMunicipiosByDepartamentoId(departamentoId: String):DataResponse<MunicipioModel>{
        val response:DataResponse<MunicipioModel> = municipiosService.getMunicipiosByDepartamentoId(departamentoId)
        if (response.status == "success"){
            if (response.data != null){
                MunicipiosProvider.municipiosList = response.data
            }
        }
        return response
    }
}