package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.network.services.FincasService
import com.vanesa.silvopastorilapp.data.providers.FincasProvider

class FincasRepository {
    val fincasService = FincasService()

    suspend fun postFinca(fincaDTO: FincaDTO):DataResponse<String>{
        return fincasService.postFinca(fincaDTO)
//        if (response.status == "success"){
//            if (response.data.isNotEmpty()){
//                MunicipiosProvider.municipiosList = response.data
//            }
//        }
        //return response
    }
    suspend fun updateFinca(fincaId: String, fincaDTO: FincaDTO):DataResponse<String>{
        return fincasService.updateFinca(fincaId, fincaDTO)
    }

    suspend fun getFincasByUserId(userId: String):DataResponse<FincaModel>{
        val response:DataResponse<FincaModel> = fincasService.getFincasByUserId(userId)
        if (response.status == "success"){
            if (response.data != null){
                FincasProvider.fincasList = response.data
            }
        }
        return response
    }
}