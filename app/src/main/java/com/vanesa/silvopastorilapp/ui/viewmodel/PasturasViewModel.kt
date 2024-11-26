package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.domain.GetPasturasUseCase
import kotlinx.coroutines.launch

class PasturasViewModel : ViewModel() {

    val dataResponsePasturas = MutableLiveData<DataResponse<PasturaModel>>()
    val pasturasList = MutableLiveData<List<PasturaModel>?>()

    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()

    // Invocacion de los casos de uso
    var getPasturasUseCase = GetPasturasUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<PasturaModel> = getPasturasUseCase()
            dataResponsePasturas.postValue(result)

            when (result.status) {
                "success" -> {
                    val listaPastura: List<PasturaModel>? = result.data
                    if (!listaPastura.isNullOrEmpty()) {
                        pasturasList.postValue(listaPastura)
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }
}
