package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.databinding.ActivityDescSilvoBinding

class DescSilvoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDescSilvoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDescSilvoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val titulo = intent.getStringExtra("TITULO")
        val descripcion = intent.getStringExtra("DESC")

        binding.tvTitulo.text = titulo
        binding.txtDesc.text = descripcion

        binding.Casa.setOnClickListener {
            val intent = Intent(binding.root.context, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}