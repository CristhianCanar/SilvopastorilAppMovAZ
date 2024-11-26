package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.domain.PostSignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private val signUpModel = MutableLiveData<DataResponse<UserModel>>()
    val userModel = MutableLiveData<UserModel>()
    val message = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    var loginUseCase = PostSignUpUseCase()

    fun signUp(signUpDTO: SignUpDTO){
        viewModelScope.launch {
            isLoading.postValue(true)

            val result:DataResponse<UserModel> = loginUseCase(signUpDTO)
            signUpModel.postValue(result)

            when(result.status){
                "success" ->{
                    userModel.postValue(result.data[0]!!)
                    isLoading.postValue(false)
                }
                "invalid" ->{
                    message.postValue(result.message)
                    isLoading.postValue(false)
                }
                "error" ->{
                    message.postValue("Error al registrar el usuario \uD83D\uDE14 Error: ${result.message}")
                    isLoading.postValue(false)
                }
            }
        }
    }
}