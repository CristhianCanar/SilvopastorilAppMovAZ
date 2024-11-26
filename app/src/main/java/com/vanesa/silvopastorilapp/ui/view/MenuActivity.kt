package com.vanesa.silvopastorilapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.databinding.ActivityMenuBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = SharedApp.prefs.name
        binding.tvBienvenido.text = "Bienvenido(a) $name"

        binding.BtnPotreros.setOnClickListener {
            val intent = Intent(binding.root.context, RotacionActivity::class.java)
            startActivity(intent)
        }

        binding.BtnSilvopastoril.setOnClickListener {
            val intent = Intent(binding.root.context, SistemaSilvoActivity::class.java)
            startActivity(intent)
        }

        binding.BtnForraje.setOnClickListener {
            val intent = Intent(binding.root.context, PasturasActivity::class.java)
            startActivity(intent)
        }

        binding.BtnAforo.setOnClickListener {
            val intent = Intent(binding.root.context, PasturasActivity::class.java)
            startActivity(intent)
        }

        binding.btnFincas.setOnClickListener{
            val intent = Intent(binding.root.context, FincasActivity::class.java)
            startActivity(intent)
        }

        binding.ivLogout.setOnClickListener {
            showAlertDialog(binding.root.context)
        }
    }

    private fun showAlertDialog(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle("Cerrar sesión")
            .setMessage("¿Seguro que desea salir de la app?")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Si") { dialog, _ ->
                showLogin()
                dialog.dismiss()
            }
            .show()
    }

    private fun showLogin() {
        val intent = Intent(binding.root.context, LoginActivity::class.java)
        SharedApp.prefs.id = ""
        SharedApp.prefs.name = ""
        SharedApp.prefs.jwt = ""
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}