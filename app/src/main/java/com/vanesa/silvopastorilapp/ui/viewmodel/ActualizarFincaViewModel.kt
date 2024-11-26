package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.domain.GetDepartamentosUseCase
import com.vanesa.silvopastorilapp.domain.GetMunicipiosByDepartamentoIdUseCase
import com.vanesa.silvopastorilapp.domain.GetPasturasPartialUseCase
import com.vanesa.silvopastorilapp.domain.PostFincaUseCase
import com.vanesa.silvopastorilapp.domain.PostUpdateFincaUseCase
import kotlinx.coroutines.launch

class ActualizarFincaViewModel : ViewModel() {
    // Departamentos
    val dataResponseDepartamentos = MutableLiveData<DataResponse<DepartamentoModel>>()
    val departamentosList = MutableLiveData<List<DepartamentoModel>?>()

    // Municipios
    val dataResponseMunicipios = MutableLiveData<DataResponse<MunicipioModel>>()
    val municipiosList = MutableLiveData<List<MunicipioModel>?>()

    // Pasturas
    val dataResponsePasturas = MutableLiveData<DataResponse<PasturaPartialModel>>()
    val pasturasList = MutableLiveData<List<PasturaPartialModel>?>()

    // Fincas
    val dataResponseFinca = MutableLiveData<Boolean>()

    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String?>()

    // Invocacion de los casos de uso
    var getDepartamentosUseCase = GetDepartamentosUseCase()
    var getMunicipiosByDepartamentoIdUseCase = GetMunicipiosByDepartamentoIdUseCase()
    var getPasturasPartialUseCase = GetPasturasPartialUseCase()
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<DepartamentoModel> = getDepartamentosUseCase()
            dataResponseDepartamentos.postValue(result)

            when (result.status) {
                "success" -> {
                    val listaDep: List<DepartamentoModel>? = result.data
                    if (!listaDep.isNullOrEmpty()) {
                        departamentosList.postValue(listaDep)
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }

    var updateFincaUseCase = PostUpdateFincaUseCase()

    fun getPasturas() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<PasturaPartialModel> = getPasturasPartialUseCase()
            dataResponsePasturas.postValue(result)

            when (result.status) {
                "success" -> {
                    val listaPasturas: List<PasturaPartialModel>? = result.data
                    if (!listaPasturas.isNullOrEmpty()) {
                        pasturasList.postValue(listaPasturas)
                        isLoading.postValue(false)
                    }
                }
            }
        }
    }

    fun getMunicipiosByDepartamentoId(departamentoId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<MunicipioModel> =
                getMunicipiosByDepartamentoIdUseCase(departamentoId)
            dataResponseMunicipios.postValue(result)

            when (result.status) {
                "success" -> {
                    val listaDep: List<MunicipioModel>? = result.data
                    if (!listaDep.isNullOrEmpty()) {
                        municipiosList.postValue(listaDep)
                        isLoading.postValue(false)
                        // Obtener las pasturas
                        getPasturas()
                    }
                }
            }
        }
    }

    fun updateFinca(fincaId: String, fincaDTO: FincaDTO) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: DataResponse<String> = updateFincaUseCase(fincaId, fincaDTO)
            when (result.status) {
                "success" -> {
                    message.postValue(result.message)
                    if (result.data[0] == "updated") {
                        message.postValue("Finca actualizada de manera exitosa")
                        isLoading.postValue(false)
                        dataResponseFinca.postValue(true)
                    }
                }
                "error" -> {
                    message.postValue(result.message)
                    isLoading.postValue(false)
                    dataResponseFinca.postValue(false)
                }
            }
        }
    }
}