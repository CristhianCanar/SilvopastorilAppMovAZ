package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vanesa.silvopastorilapp.data.dto.SignUpDTO
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.data.utils.Utils
import com.vanesa.silvopastorilapp.databinding.ActivitySignUpBinding
import com.vanesa.silvopastorilapp.ui.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val utils: Utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        signUpViewModel.userModel.observe(this, Observer {
            if (it != null) {
                SharedApp.prefs.id = it.id.toString()
                SharedApp.prefs.name = it.name.toString()
                SharedApp.prefs.jwt = it.jwt.toString()
                showRegistroFinca()
                showToastDialog("Registro de usuario exitoso, ¡no abandone el proceso en este punto!")
            }
        })

        binding.btnFinca.setOnClickListener {
            if (validateData()) {
                when {
                    utils.isConnected(binding.root.context) -> {
                        val name: String = binding.txtName.text.toString()
                        val phone: String = binding.etTelefono.text.toString()
                        val email: String = binding.txtUser.text.toString()
                        val password: String = binding.txtPassword.text.toString()

                        val loginDTO = SignUpDTO(name,phone, email, password)
                        signUpViewModel.signUp(loginDTO)
                    }

                    !utils.isConnected(binding.root.context) -> {
                        showToastDialog("No tienes conexión a internet \uD83D\uDE32 !")
                    }
                }
            }
        }


    }
    private fun showRegistroFinca() {
        val intent = Intent(binding.root.context, RegistroFincaActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showToastDialog(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun validateData(): Boolean {
        var isValid = true
        with(binding){
            // validar el null del campo nombre
            if (txtName.text.toString().isBlank()){
                isValid = false
                txtName.error = "Campo requerido"
            } else {
                txtName.error = null
            }

            if (etTelefono.text.toString().isBlank()){
                isValid = false
                etTelefono.error = "Campo requerido"
            } else {
                etTelefono.error = null
            }
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