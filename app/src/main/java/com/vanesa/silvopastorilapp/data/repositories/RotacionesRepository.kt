package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.network.services.RotacionesService

import com.vanesa.silvopastorilapp.data.providers.RotacionesProvider

class RotacionesRepository {
    val rotacionesService = RotacionesService()

    suspend fun postRotacion(rotacionDTO: RotacionDTO):DataResponse<RotacionModel>{
        return rotacionesService.postRotacion(rotacionDTO)
    }


    suspend fun getRotacionesByUserId(userId: String):DataResponse<RotacionModel>{
        val response:DataResponse<RotacionModel> = rotacionesService.getRotacionesByUserId(userId)

        if (response.status == "success"){
            if (response.data != null){
                RotacionesProvider.rotacionesList = response.data
            }
        }
        return response
    }
}