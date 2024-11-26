package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.vanesa.silvopastorilapp.data.dto.LoginDTO
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.data.utils.Utils
import com.vanesa.silvopastorilapp.databinding.ActivityInicioSesionBinding
import com.vanesa.silvopastorilapp.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val utils: Utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = SharedApp.prefs.id

        if (userId.isNotEmpty()){
            showMenu()
        }

        loginViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        loginViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        loginViewModel.userModel.observe(this, Observer {
            if (it != null) {
                SharedApp.prefs.id = it.id.toString()
                SharedApp.prefs.name = it.name.toString()
                SharedApp.prefs.jwt = it.jwt.toString()
                showMenu()
            }
        })

        binding.BtnIniciar.setOnClickListener {
            if (validateData()) {
                when {
                    utils.isConnected(binding.root.context) -> {
                        val user: String = binding.txtUser.text.toString()
                        val password: String = binding.txtPassword.text.toString()

                        val loginDTO = LoginDTO(user, password)
                        loginViewModel.signIn(loginDTO)
                        clearInputs()
                    }

                    !utils.isConnected(binding.root.context) -> {
                        showToastDialog("No tienes conexión a internet \uD83D\uDE32 !")
                    }
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            showSignUp()
        }
    }

    private fun clearInputs() {
        binding.txtUser.setText("")
        binding.txtPassword.setText("")
    }

    private fun showMenu() {
        val intent = Intent(binding.root.context, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showSignUp() {
        val intent = Intent(binding.root.context, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun showToastDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun validateData(): Boolean {
        var isValid = true
        with(binding){
            // validar el null del campo correo
            if (txtUser.text.toString().isBlank()){
                isValid = false
                txtUser.error = "Campo requerido"
            } else {
                txtUser.error = null
            }
            // validar el null del campo contraseña
            if (txtPassword.text.toString().isBlank()){
                isValid = false
                txtPassword.error = "Campo requerido"
            } else {
                txtPassword.error = null
            }
        }
        return isValid
    }
}