package com.vanesa.silvopastorilapp.data.repositories

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.data.network.services.PasturasService
import com.vanesa.silvopastorilapp.data.providers.PasturasProvider

class PasturasRepository {
    val pasturasService = PasturasService()

    suspend fun getPasturasPartial():DataResponse<PasturaPartialModel>{
        val response:DataResponse<PasturaPartialModel> = pasturasService.getPasturasPartial()
        if (response.status == "success"){
            if (response.data != null){
                PasturasProvider.pasturasPartialList = response.data
            }
        }
        return response
    }

    // Aqui va el nuevo metodo para obtener todas las pasturas
    suspend fun getPasturas():DataResponse<PasturaModel>{
        val response:DataResponse<PasturaModel> = pasturasService.getPasturas()
        if (response.status == "success"){
            if (response.data != null){
                PasturasProvider.pasturasList = response.data
            }
        }
        return response
    }
}