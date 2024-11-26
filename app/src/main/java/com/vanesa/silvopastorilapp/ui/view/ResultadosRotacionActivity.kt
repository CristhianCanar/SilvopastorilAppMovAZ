package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.databinding.ActivityResultadosRotacionBinding

class ResultadosRotacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadosRotacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultadosRotacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fechaCreacion = intent.getStringExtra("FECHA_CREACION")
        val nombreFinca = intent.getStringExtra("FINCA")
        val periodoOcupacion = intent.getStringExtra("PERIODO_OCUPACION")
        val pesoAnimalProm = intent.getStringExtra("PESO_ANIMAL_PROM")
        val aforo = intent.getStringExtra("AFORO")
        val numeroPotreros = intent.getStringExtra("NUMERO_POTREROS")
        val areaCadaPotrero = intent.getStringExtra("AREA_CADA_POTRERO")
        val forrajeTotal = intent.getStringExtra("FORRAJE_TOTAL")
        val forrajeDisponible = intent.getStringExtra("FORRAJE_DISPONIBLE")
        val totalAnimales = intent.getStringExtra("TOTAL_ANIMALES")

        binding.tvFechaCreacion.text = fechaCreacion
        binding.tvFinca.text = nombreFinca
        binding.tvPeriodoOcupacion.text = periodoOcupacion
        binding.tvPesoAnimalProm.text = pesoAnimalProm
        binding.tvAforo.text = aforo
        binding.tvNumeroPotreros.text = numeroPotreros
        binding.tvAreaCadaPotrero.text = areaCadaPotrero
        binding.tvForrajeTotal.text = forrajeTotal
        binding.tvForrajeDisponible.text = forrajeDisponible
        binding.tvTotalAnimales.text = totalAnimales

        binding.Casa.setOnClickListener {
            val intent = Intent(binding.root.context, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}