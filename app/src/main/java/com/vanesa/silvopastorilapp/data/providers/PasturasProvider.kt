package com.vanesa.silvopastorilapp.data.providers

import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel

class PasturasProvider {
    companion object {
        var pasturasPartialList: List<PasturaPartialModel>? = emptyList()
        var pasturasList: List<PasturaModel>? = emptyList()
    }
}