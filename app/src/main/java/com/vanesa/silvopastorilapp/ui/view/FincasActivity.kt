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
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.databinding.ActivityFincasBinding
import com.vanesa.silvopastorilapp.ui.adpaters.FincasRecyclerViewAdapter
import com.vanesa.silvopastorilapp.ui.viewmodel.FincasViewModel

class FincasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFincasBinding
    private var fincasList: MutableList<FincaModel> = mutableListOf()
    private val fincasViewModel: FincasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFincasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var adapterFinca =
            FincasRecyclerViewAdapter(this, fincasList)

        val userId = SharedApp.prefs.id
        fincasViewModel.getFincasByUserId(userId)

        fincasViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        fincasViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        fincasViewModel.fincasList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                fincasList = it as MutableList<FincaModel>
                adapterFinca = FincasRecyclerViewAdapter(this, fincasList)
                binding.rvListadoFincas.adapter = adapterFinca
                binding.rvListadoFincas.layoutManager = LinearLayoutManager(this)
            }
        })

        fincasViewModel.dataResponseFincas.observe(this, Observer {
            if (it != null) {
                binding.rvListadoFincas.isVisible = false
                binding.tvDataInfo.isVisible = true
                binding.tvDataInfo.text = it
            }
        })

        binding.ivCrear.setOnClickListener {
            val intent = Intent(binding.root.context, RegistroFincaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showToastDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}