package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.databinding.ActivityPasturasBinding
import com.vanesa.silvopastorilapp.ui.adpaters.PasturasAdapter
import com.vanesa.silvopastorilapp.ui.viewmodel.PasturasViewModel

class PasturasActivity : AppCompatActivity() {
    private val pasturasViewModel: PasturasViewModel by viewModels()

    private lateinit var binding: ActivityPasturasBinding
    private var pasturasList: MutableList<PasturaModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPasturasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapterPasturas = PasturasAdapter(this, pasturasList)

        pasturasViewModel.onCreate()

        pasturasViewModel.pasturasList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                pasturasList = it as MutableList<PasturaModel>
                adapterPasturas =
                    PasturasAdapter(this, pasturasList)
                binding.rvListadoPasturas.adapter = adapterPasturas
                binding.rvListadoPasturas.layoutManager = LinearLayoutManager(this)
            }
        })

        pasturasViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
    }
}