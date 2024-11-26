package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.domain.GetFincasByUserIdUseCase
import kotlinx.coroutines.launch

class FincasViewModel : ViewModel() {
    // Fincas
    val dataResponseFincas = MutableLiveData<String>()
    val fincasList = MutableLiveData<List<FincaModel>?>()

    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()

    // Invocacion de los casos de uso
    var getFincasByUserIdUseCase = GetFincasByUserIdUseCase()

    fun getFincasByUserId(userId:String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<FincaModel> = getFincasByUserIdUseCase(userId)
            when (result.status) {
                "success" -> {
                    val listaFincas: List<FincaModel>? = result.data
                    if (!listaFincas.isNullOrEmpty()) {
                        fincasList.postValue(listaFincas)
                        isLoading.postValue(false)
                    }
                    else {
                        dataResponseFincas.postValue("No hay fincas registradas 😔")
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }
}