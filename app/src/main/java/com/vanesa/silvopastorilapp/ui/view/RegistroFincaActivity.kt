package com.vanesa.silvopastorilapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.dto.FincaDTO
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel
import com.vanesa.silvopastorilapp.data.model.MunicipioModel
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.databinding.ActivityRegistroFincaBinding
import com.vanesa.silvopastorilapp.ui.adpaters.DepartamentosAdapter
import com.vanesa.silvopastorilapp.ui.adpaters.MunicipiosAdapter
import com.vanesa.silvopastorilapp.ui.adpaters.PasturasPartialAdapter
import com.vanesa.silvopastorilapp.ui.viewmodel.RegistroFincaViewModel

class RegistroFincaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroFincaBinding
    private val registroFincaViewModel: RegistroFincaViewModel by viewModels()
    private var departamentosList: MutableList<DepartamentoModel> = mutableListOf()
    private var municipiosList: MutableList<MunicipioModel> = mutableListOf()
    private var pasturasPartialList: MutableList<PasturaPartialModel> = mutableListOf()

    // Datos Finca
    private var userId: String = ""
    private var pasturaId: String = ""
    private var municipioId: String = ""
    private var ubicacion: String = ""
    private var nombre: String = ""
    private var areaTotal: String = ""
    private var areaDisponible: String = ""
    private var cantidadGanado: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroFincaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = SharedApp.prefs.id

        var adapterDepto =
            DepartamentosAdapter(this, R.layout.item_departamentos, departamentosList)
        var adapterMunicipio = MunicipiosAdapter(this, R.layout.item_departamentos, municipiosList)
        var adapterPasturasPartial = PasturasPartialAdapter(this, R.layout.item_departamentos, pasturasPartialList)

        registroFincaViewModel.onCreate()

        registroFincaViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        registroFincaViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        registroFincaViewModel.departamentosList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                departamentosList = it as MutableList<DepartamentoModel>
                adapterMunicipio = MunicipiosAdapter(this, R.layout.item_municipios, municipiosList)
                adapterDepto =
                    DepartamentosAdapter(this, R.layout.item_departamentos, departamentosList)
                binding.aCdepto.setAdapter(adapterDepto)
            }
        })

        registroFincaViewModel.municipiosList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                municipiosList = it as MutableList<MunicipioModel>
                adapterMunicipio = MunicipiosAdapter(this, R.layout.item_municipios, municipiosList)
                binding.aCMunicipio.setAdapter(adapterMunicipio)
            }
        })

        registroFincaViewModel.pasturasList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                pasturasPartialList = it as MutableList<PasturaPartialModel>
                adapterPasturasPartial = PasturasPartialAdapter(this, R.layout.item_municipios, pasturasPartialList)
                binding.aCPastura.setAdapter(adapterPasturasPartial)
            }
        })

        registroFincaViewModel.dataResponseFinca.observe(this, Observer {
            if (it) {
                showMenu()
            }
        })

        binding.aCdepto.setOnItemClickListener { parent, view, position, id ->
            val idDepartamento = adapterDepto.getItemIdAtPosition(position)
            // Limpiar los inputText que se modifican si se cambia el depto
            binding.aCMunicipio.setText("")
            binding.TxtTropico.setText("")
            registroFincaViewModel.getMunicipiosByDepartamentoId(idDepartamento!!)
            // Toast.makeText(this, "Seleccionado: $nombreDepartamento (ID: $idDepartamento)", Toast.LENGTH_SHORT).show()
        }

        binding.aCMunicipio.setOnItemClickListener { parent, view, position, id ->
            val tipoTropico: String? = adapterMunicipio.getTropicoAtPosition(position)
            municipioId = adapterMunicipio.getItemIdAtPosition(position).toString()
            binding.TxtTropico.setText(tipoTropico)
        }

        binding.aCPastura.setOnItemClickListener { parent, view, position, id ->
            pasturaId = adapterPasturasPartial.getItemIdAtPosition(position).toString()
        }

        binding.BtnGuardar.setOnClickListener {
            if (validateData()) {
                ubicacion = binding.TxtUbicacion.text.toString()
                nombre = binding.TxtNombre.text.toString()
                areaTotal = binding.TxtArea.text.toString()
                areaDisponible = binding.TxtAreaDis.text.toString()
                cantidadGanado = binding.TxtGanado.text.toString()
                val fincaDTO: FincaDTO = FincaDTO(
                    userId,
                    municipioId,
                    pasturaId,
                    ubicacion,
                    nombre,
                    areaTotal,
                    areaDisponible,
                    cantidadGanado
                )
                storeFinca(fincaDTO)
            }
        }
    }

    private fun storeFinca(fincaDTO: FincaDTO) {
        registroFincaViewModel.postFinca(fincaDTO)
    }

    private fun showMenu() {
        startActivity(Intent(binding.root.context, MenuActivity::class.java))
        finish()
        clearInputs()
    }

    private fun showToastDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearInputs() {
        binding.TxtUbicacion.setText("")
        binding.TxtNombre.setText("")
        binding.aCdepto.text.clear()
        binding.aCMunicipio.text.clear()
        binding.TxtTropico.setText("")
        binding.TxtArea.setText("")
        binding.TxtAreaDis.setText("")
        binding.aCPastura.text.clear()
        binding.TxtGanado.setText("")
    }
    private fun validateData(): Boolean {
        var isValid = true
        with(binding){
            // validar el null del campo nombre
            if (TxtNombre.text.toString().isBlank()){
                isValid = false
                Nombre.error = "Campo requerido"
            } else {
                Nombre.error = null
            }
            // validar el null del campo departamento
            if (aCdepto.text.toString().isBlank()){
                isValid = false
                Departamento.error = "Campo requerido"
            } else {
                Departamento.error = null
            }
            // validar el null del campo periodo de municipio
            if (aCMunicipio.text.toString().isBlank()){
                isValid = false
                Municipio.error = "Campo requerido"
            } else {
                Municipio.error = null
            }
            // validar el null del campo trópico
            if (TxtTropico.text.toString().isBlank()){
                isValid = false
                Tropico.error = "Campo requerido"
            } else {
                Tropico.error = null
            }
            // validar el null del campo ubicación
            if (TxtUbicacion.text.toString().isBlank()){
                isValid = false
                Ubicacion.error = "Campo requerido"
            } else {
                Ubicacion.error = null
            }
            // validar el null del campo pasturas
            if (aCPastura.text.toString().isBlank()){
                isValid = false
                Pastura.error = "Campo requerido"
            } else {
                Pastura.error = null
            }
            // validar el null del campo area total
            if (aCMunicipio.text.toString().isBlank()){
                isValid = false
                Municipio.error = "Campo requerido"
            } else {
                Municipio.error = null
            }
            // validar el null del campo area disponible
            if (TxtTropico.text.toString().isBlank()){
                isValid = false
                Tropico.error = "Campo requerido"
            } else {
                Tropico.error = null
            }
            // validar el null del campo ganado
            if (TxtGanado.text.toString().isBlank()){
                isValid = false
                Ganado.error = "Campo requerido"
            } else {
                Ganado.error = null
            }
        }
        return isValid
    }
}