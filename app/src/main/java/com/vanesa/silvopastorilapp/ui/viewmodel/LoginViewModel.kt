package com.vanesa.silvopastorilapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.model.DataResponse
import com.vanesa.silvopastorilapp.data.model.UserModel
import com.vanesa.silvopastorilapp.domain.PostSignInUseCase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val loginModel = MutableLiveData<DataResponse<UserModel>>()
    val userModel = MutableLiveData<UserModel>()
    val message = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    var loginUseCase = PostSignInUseCase()

    fun signIn(loginDTO: LoginDTO){
        viewModelScope.launch {
            isLoading.postValue(true)

            val result:DataResponse<UserModel> = loginUseCase(loginDTO)
            loginModel.postValue(result)

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
                    message.postValue("Usuario no registrado! \uD83D\uDE14")
                    isLoading.postValue(false)
                }
            }
        }
    }
}