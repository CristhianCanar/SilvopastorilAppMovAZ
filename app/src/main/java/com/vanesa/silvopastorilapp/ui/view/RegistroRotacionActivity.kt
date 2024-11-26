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
import com.vanesa.silvopastorilapp.data.dto.RotacionDTO
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.data.utils.SharedApp
import com.vanesa.silvopastorilapp.databinding.ActivityRegistroRotacionBinding
import com.vanesa.silvopastorilapp.ui.adpaters.FincasAdapter
import com.vanesa.silvopastorilapp.ui.viewmodel.RegistroRotacionViewModel

class RegistroRotacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroRotacionBinding
    private val registroRotacionViewModel: RegistroRotacionViewModel by viewModels()
    private var fincasList: MutableList<FincaModel> = mutableListOf()

    // Datos Rotacion
    private var fincaId: String? = "1"
    private var periodoOcupacion: Int = 1
    private var pesoAnimalProm: Double? = null
    private var aforo: Double? = null
    private var numeroPotreros: Double? = null
    private var areaCadaPotrero: Double? = null
    private var forrajeTotal: Double? = null
    private var forrajeDisponible: Double? = null
    private var totalAnimales: Double? = null

    private var areaTotal: String = ""
    private var periodoRecuperacion: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroRotacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var adapterFinca =
            FincasAdapter(this, R.layout.item_fincas, fincasList)

        val userId = SharedApp.prefs.id
        registroRotacionViewModel.getFincasByUserId(userId)

        registroRotacionViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        registroRotacionViewModel.message.observe(this, Observer {
            if (it != null) {
                showToastDialog(it)
            }
        })

        registroRotacionViewModel.fincasList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                fincasList = it as MutableList<FincaModel>
                adapterFinca = FincasAdapter(this, R.layout.item_fincas, fincasList)

                binding.etFincaId.setAdapter(adapterFinca)
            }
        })

        binding.etFincaId.setOnItemClickListener { parent, view, position, id ->
            fincaId = adapterFinca.getItemIdAtPosition(id.toInt())
            areaTotal = adapterFinca.getAreaTotalAtPosition(position)
            periodoRecuperacion = adapterFinca.getPeriodoRecuperacionAtPosition(position)
            binding.etAreaTotal.setText(areaTotal)
            binding.etPeriodoRecuperacion.setText(periodoRecuperacion)
        }

        binding.btnGuardar.setOnClickListener {
            if (validateData()) {
                periodoOcupacion = binding.etPeriodoOcupacion.text.toString().toInt()
                pesoAnimalProm = binding.etPesoAnimalProm.text.toString().toDouble()
                aforo = binding.etAforo.text.toString().toDouble()
                // Formula numero de potreros
                numeroPotreros = (periodoRecuperacion.toDouble() / periodoOcupacion) + 1
                // Formula area cada potrero
                areaCadaPotrero = areaTotal.toDouble() / numeroPotreros!!
                // Formula forraje verde total
                forrajeTotal = areaCadaPotrero!! * aforo!!
                // Formula forraje verde disponible
                forrajeDisponible = forrajeTotal!! * 0.7
                // Formula animales a pastorear
                totalAnimales = forrajeDisponible!! / (pesoAnimalProm!! * 0.15)

                val rotacionDTO: RotacionDTO = RotacionDTO(
                    fincaId!!,
                    periodoOcupacion.toString(),
                    pesoAnimalProm.toString(),
                    aforo.toString(),
                    numeroPotreros.toString(),
                    areaCadaPotrero.toString(),
                    forrajeTotal.toString(),
                    forrajeDisponible.toString(),
                    totalAnimales.toString()
                )
                storeRotacion(rotacionDTO)
            }
        }

        registroRotacionViewModel.rotacionModel.observe(this, Observer {
            if (it != null) {
                showResults(it)
            }
        })
    }

    private fun showResults(rotacionModel: RotacionModel) {
        val intent = Intent(this, ResultadosRotacionActivity::class.java)
        intent.putExtra("FECHA_CREACION", rotacionModel.createdAt)
        intent.putExtra("FINCA", rotacionModel.fincas!!.nombre)
        intent.putExtra("PERIODO_OCUPACION", rotacionModel.periodoOcupacion+" días")
        intent.putExtra("PESO_ANIMAL_PROM", rotacionModel.pesoAnimalProm+" kg")
        intent.putExtra("AFORO", rotacionModel.aforo+" kg/m²")
        intent.putExtra("NUMERO_POTREROS", rotacionModel.numeroPotreros)
        intent.putExtra("AREA_CADA_POTRERO", rotacionModel.areaCadaPotrero+" m²")
        intent.putExtra("FORRAJE_TOTAL", rotacionModel.forrajeTotal+" kg/m²")
        intent.putExtra("FORRAJE_DISPONIBLE", rotacionModel.forrajeDisponible+" kg/m²")
        intent.putExtra("TOTAL_ANIMALES", rotacionModel.totalAnimales)
        startActivity(intent)
        finish()
    }

    private fun storeRotacion(rotacionDTO: RotacionDTO) {
        registroRotacionViewModel.postRotacion(rotacionDTO)
    }

    private fun showToastDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearInputs() {
        binding.etFincaId.text.clear()
        binding.etAreaTotal.setText("")
        binding.etPeriodoRecuperacion.setText("")
        binding.etPeriodoOcupacion.setText("")
        binding.etPesoAnimalProm.setText("")
        binding.etAforo.setText("")
    }
    private fun validateData(): Boolean {
        var isValid = true
        with(binding){
            // validar el null del campo finca
            if (etFincaId.text.toString().isBlank()){
                isValid = false
                tiFincaId.error = "Campo requerido"
            } else {
                tiFincaId.error = null
            }
            // validar el null del campo area total
            if (etAreaTotal.text.toString().isBlank()){
                isValid = false
                tiAreaTotal.error = "Debe seleccionar finca"
            } else {
                tiAreaTotal.error = null
            }
            // validar el null del campo periodo de recuperación
            if (etPeriodoRecuperacion.text.toString().isBlank()){
                isValid = false
                tiPeriodoRecuperacion.error = "Debe seleccionar finca"
            } else {
                tiPeriodoRecuperacion.error = null
            }
            // validar el null del campo peso animal
            if (etPesoAnimalProm.text.toString().isBlank()){
                isValid = false
                tiPesoAnimalProm.error = "Campo requerido"
            } else {
                tiPesoAnimalProm.error = null
            }
            // validar el null del campo aforo
            if (etAforo.text.toString().isBlank()){
                isValid = false
                tiAforo.error = "Campo requerido"
            } else {
                tiAforo.error = null
            }
        }
        return isValid
    }
}