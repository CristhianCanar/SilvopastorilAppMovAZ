package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.domain.GetFincasByUserIdUseCase
import com.vanesa.silvopastorilapp.domain.PostRotacionUseCase
import kotlinx.coroutines.launch

class RegistroRotacionViewModel : ViewModel() {
    // Fincas
    val dataResponseFincas = MutableLiveData<DataResponse<FincaModel>>()
    val fincasList = MutableLiveData<List<FincaModel>?>()
    // Rotaciones
    val dataResponseRotacion = MutableLiveData<Boolean>()
    val rotacionModel = MutableLiveData<RotacionModel>()

    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()

    // Invocacion de los casos de uso
    var getFincasByUserIdUseCase = GetFincasByUserIdUseCase()
    var postRotacionUseCase = PostRotacionUseCase()

    fun getFincasByUserId(userId:String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<FincaModel> = getFincasByUserIdUseCase(userId)
            dataResponseFincas.postValue(result)
            when (result.status) {
                "success" -> {
                    val listaFincas: List<FincaModel>? = result.data
                    if (!listaFincas.isNullOrEmpty()) {
                        fincasList.postValue(listaFincas)
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }

    fun postRotacion(rotacionDTO: RotacionDTO) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<RotacionModel> = postRotacionUseCase(rotacionDTO)
            when (result.status) {
                "success" -> {
                    message.postValue("Rotacion creada de manera exitosa")
                    rotacionModel.postValue(result.data[0])
                    isLoading.postValue(false)
                    dataResponseRotacion.postValue(true)
                }
                "error" -> {
                    message.postValue(result.message)
                    isLoading.postValue(false)
                    dataResponseRotacion.postValue(false)
                }
            }
        }
    }

}