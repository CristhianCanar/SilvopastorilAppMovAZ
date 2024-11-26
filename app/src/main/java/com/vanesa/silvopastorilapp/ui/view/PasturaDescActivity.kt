package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.databinding.ActivityForrajeDescBinding
import com.squareup.picasso.Picasso

class PasturaDescActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForrajeDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForrajeDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("NOMBRE_PASTURA")
        val recuperacion = intent.getStringExtra("REC_PASTURA")
        val tipo_suelo = intent.getStringExtra("TIPO_SUELO_PASTURA")
        val tropico = intent.getStringExtra("TROPICO_PASTURA")
        val descripcion = intent.getStringExtra("DES_PASTURA")
        val imagen = intent.getStringExtra("URL_IMG_PASTURA")

        binding.tvNombreForraje.text = nombre
        binding.tvRecuperacion.text = recuperacion
        binding.tvSuelo.text = tipo_suelo
        binding.tvMsnm.text = tropico
        binding.tvDescripcion.text = descripcion
        Picasso.get()
            .load(imagen) // La URL de la imagen
            //.placeholder(R.drawable.placeholder) // Imagen de placeholder opcional
            .error(R.drawable.arbol) // Imagen de error opcional
            .into(binding.ivPastura) // Tu ImageView

        binding.Casa.setOnClickListener {
            val intent = Intent(binding.root.context, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}