package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.ui.view.PasturaDescActivity
import com.vanesa.silvopastorilapp.data.model.FincaModel
import com.vanesa.silvopastorilapp.ui.view.ActualizarFincaActivity

class FincasRecyclerViewAdapter (
    private val context: Context,
    private val fincaList: List<FincaModel>,
    ) : RecyclerView.Adapter<FincasRecyclerViewAdapter.FincasViewHolder>(){

    class FincasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvFinca)
        val btnEditar: ImageView = itemView.findViewById(R.id.ivDetalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FincasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fincas_list, parent, false)
        return FincasViewHolder(view)
    }

    override fun onBindViewHolder(holder: FincasViewHolder, position: Int) {
        val finca = fincaList[position]
        holder.tvNombre.text = finca.nombre

        holder.btnEditar.setOnClickListener {
            val intent = Intent(context, ActualizarFincaActivity::class.java)
            intent.putExtra("id_finca", finca.idFinca)
            intent.putExtra("user_id", finca.userId)
            intent.putExtra("municipio_id", finca.municipioId)
            intent.putExtra("pastura_id", finca.pasturaId)
            intent.putExtra("ubicacion", finca.ubicacion)
            intent.putExtra("nombre", finca.nombre)
            intent.putExtra("area_total", finca.areaTotal)
            intent.putExtra("area_disponible", finca.areaDisponible)
            intent.putExtra("aforo_prom", finca.aforoProm)
            intent.putExtra("num_potreros", finca.numPotreros)
            intent.putExtra("cantidad_ganado", finca.cantidadGanado)
            intent.putExtra("departamento", finca.municipios!!.departamentos!!.nombre)
            intent.putExtra("municipio", finca.municipios.nombre)
            intent.putExtra("pastura", finca.pasturas!!.nombre)
            intent.putExtra("tropico", finca.municipios.tipoTropico)
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = fincaList.size
}
