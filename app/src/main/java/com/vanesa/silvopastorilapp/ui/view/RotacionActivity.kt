package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.databinding.ActivityRotacionBinding
import com.vanesa.silvopastorilapp.ui.adpaters.RotacionAdapter
import com.vanesa.silvopastorilapp.ui.viewmodel.RotacionesViewModel

class RotacionActivity : AppCompatActivity() {
    private val rotacionesViewModel: RotacionesViewModel by viewModels()

    private var rotacionesList: MutableList<RotacionModel> = mutableListOf()
    private lateinit var binding: ActivityRotacionBinding

    private var userId = "1"
    lateinit var adapterRotacion: RotacionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRotacionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        rotacionesViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        rotacionesViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        rotacionesViewModel.rotacionesList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                rotacionesList = it as MutableList<RotacionModel>
                adapterRotacion = RotacionAdapter(this, rotacionesList)

                binding.rvListadoRotacion.adapter = adapterRotacion
                binding.rvListadoRotacion.layoutManager = LinearLayoutManager(this)
            }
        })

        rotacionesViewModel.dataResponseRotacion.observe(this, Observer {
            if (it != null) {
                binding.rvListadoRotacion.isVisible = false
                binding.tvDataInfo.isVisible = true
                binding.tvDataInfo.text = it
            }
        })

        userId = SharedApp.prefs.id
        rotacionesViewModel.getRotacionesByUserId(userId)

        binding.ivRegistrarRotacion.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroRotacionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showToastDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}