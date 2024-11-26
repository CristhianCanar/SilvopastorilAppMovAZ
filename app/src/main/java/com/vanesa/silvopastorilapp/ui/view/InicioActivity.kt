package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnInicio.setOnClickListener {
            val intent = Intent(binding.root.context, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.BtnRegistro.setOnClickListener {
            val intent = Intent(binding.root.context, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}