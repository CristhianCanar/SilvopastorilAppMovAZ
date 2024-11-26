package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.databinding.ActivityTiposSilvoBinding

class TiposSilvoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTiposSilvoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTiposSilvoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardUno.setOnClickListener {
            val intent = Intent(binding.root.context, DescSilvoActivity::class.java)
            intent.putExtra("TITULO", "CERCAS VIVAS")
            intent.putExtra("DESC", getString(R.string.cercas))
            startActivity(intent)
        }

        binding.cardDos.setOnClickListener {
            val intent = Intent(binding.root.context, DescSilvoActivity::class.java)
            intent.putExtra("TITULO", "ARBOLES DISPERSOS")
            intent.putExtra("DESC", getString(R.string.dispersos))
            startActivity(intent)
        }

        binding.cardTres.setOnClickListener {
            val intent = Intent(binding.root.context, DescSilvoActivity::class.java)
            intent.putExtra("TITULO", "BANCOS FORRAJEROS")
            intent.putExtra("DESC", getString(R.string.bancos))
            startActivity(intent)
        }

        binding.cardCuatro.setOnClickListener {
            val intent = Intent(binding.root.context, DescSilvoActivity::class.java)
            intent.putExtra("TITULO", "ROTACIONAL")
            intent.putExtra("DESC", getString(R.string.rotacional))
            startActivity(intent)
        }
    }
}