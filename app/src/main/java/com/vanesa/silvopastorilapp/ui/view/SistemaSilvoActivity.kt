package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.databinding.ActivitySistemaSilvoBinding

class SistemaSilvoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySistemaSilvoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySistemaSilvoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipos.setOnClickListener {
            val intent = Intent(binding.root.context, TiposSilvoActivity::class.java)
            startActivity(intent)
        }
    }
}