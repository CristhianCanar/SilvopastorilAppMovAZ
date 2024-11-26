package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.RotacionModel

import com.vanesa.silvopastorilapp.domain.GetRotacionesByUserIdUseCase
import kotlinx.coroutines.launch

class RotacionesViewModel : ViewModel() {

    // Rotaciones
    val dataResponseRotacion = MutableLiveData<String>()

    val rotacionesList = MutableLiveData<List<RotacionModel>?>()

    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()

    // Invocacion de los casos de uso
    var getRotacionesByUserIdUseCase = GetRotacionesByUserIdUseCase()

    fun getRotacionesByUserId(userId:String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<RotacionModel> = getRotacionesByUserIdUseCase(userId)
            when (result.status) {
                "success" -> {
                    val listaRotaciones: List<RotacionModel>? = result.data
                    if (!listaRotaciones.isNullOrEmpty()) {
                        rotacionesList.postValue(listaRotaciones)
                        isLoading.postValue(false)
                    }
                    else {
                        dataResponseRotacion.postValue("No hay rotaciones registradas 😔")
                        isLoading.postValue(false)
                    }
                }

            }
        }
    }

}

