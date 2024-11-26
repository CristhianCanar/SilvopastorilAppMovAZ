package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.model.PasturaModel
import com.vanesa.silvopastorilapp.ui.view.PasturaDescActivity
import com.squareup.picasso.Picasso

class PasturasAdapter (
    private val context: Context,
    private val pasturaList: List<PasturaModel>,
    ) : RecyclerView.Adapter<PasturasAdapter.PasturasViewHolder>(){

    class PasturasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreF)
        val btnDetalle: ImageView = itemView.findViewById(R.id.imgForrajes)
        val btnBuscar: ImageView = itemView.findViewById(R.id.imgBuscar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasturasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pasturas, parent, false)
        return PasturasViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasturasViewHolder, position: Int) {
        val pastura = pasturaList[position]
        holder.tvNombre.text = pastura.nombre
        // Cargar la imagen usando Picasso
        Picasso.get()
            .load(pastura.imagen) // La URL de la imagen
            //.placeholder(R.drawable.placeholder) // Imagen de placeholder opcional
            .error(R.drawable.arbol) // Imagen de error opcional
            .into(holder.btnDetalle) // Tu ImageView

        holder.btnBuscar.setOnClickListener {
            val intent = Intent(context, PasturaDescActivity::class.java)
            // Pasar datos adicionales si es necesario
            intent.putExtra("NOMBRE_PASTURA", pastura.nombre)
            intent.putExtra("REC_PASTURA", pastura.recuperacion)
            intent.putExtra("TIPO_SUELO_PASTURA", pastura.tipo_suelo)
            intent.putExtra("TROPICO_PASTURA", pastura.tropico)
            intent.putExtra("DES_PASTURA", pastura.descripcion)
            intent.putExtra("URL_IMG_PASTURA", pastura.imagen)
            // Iniciar la nueva actividad
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = pasturaList.size
}
