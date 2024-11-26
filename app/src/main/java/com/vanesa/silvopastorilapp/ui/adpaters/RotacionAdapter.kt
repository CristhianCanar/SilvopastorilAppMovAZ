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
import com.vanesa.silvopastorilapp.data.model.RotacionModel
import com.vanesa.silvopastorilapp.ui.view.ResultadosRotacionActivity

class RotacionAdapter (
    private val context: Context,
    private val rotacionList: List<RotacionModel>,
    ) : RecyclerView.Adapter<RotacionAdapter.RotacionViewHolder>(){
    class RotacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitleFecha)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val btnBuscar: ImageView = itemView.findViewById(R.id.ivBtnDetalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RotacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return RotacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RotacionViewHolder, position: Int) {
        val rotacion = rotacionList[position]
        holder.tvTitle.text = rotacion.fincas!!.nombre
        holder.tvFecha.text = rotacion.createdAt
        holder.tvNombre.text = "Rotación # ${rotacionList.size-position}"
        holder.btnBuscar.setOnClickListener {
            val intent = Intent(context, ResultadosRotacionActivity::class.java)
            intent.putExtra("FECHA_CREACION", rotacion.createdAt)
            intent.putExtra("FINCA", rotacion.fincas!!.nombre)
            intent.putExtra("PERIODO_OCUPACION", rotacion.periodoOcupacion+" días")
            intent.putExtra("PESO_ANIMAL_PROM", rotacion.pesoAnimalProm+" kg")
            intent.putExtra("AFORO", rotacion.aforo+" kg/m²")
            intent.putExtra("NUMERO_POTREROS", rotacion.numeroPotreros)
            intent.putExtra("AREA_CADA_POTRERO", rotacion.areaCadaPotrero+" m²")
            intent.putExtra("FORRAJE_TOTAL", rotacion.forrajeTotal+" kg/m²")
            intent.putExtra("FORRAJE_DISPONIBLE", rotacion.forrajeDisponible+" kg/m²")
            intent.putExtra("TOTAL_ANIMALES", rotacion.totalAnimales)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = rotacionList.size
}