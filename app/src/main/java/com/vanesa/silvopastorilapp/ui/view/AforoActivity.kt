package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.databinding.ActivityAforoBinding

class AforoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAforoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAforoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}