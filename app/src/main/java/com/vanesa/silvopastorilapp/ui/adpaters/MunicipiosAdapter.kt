package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.model.MunicipioModel

class MunicipiosAdapter(context: Context,
                        resource: Int,
                        private val municipios: List<MunicipioModel>
) : ArrayAdapter<String>(context, resource) {

    private val nombresMunicipios: List<String> = municipios.map { it.nombre }
    private var filteredNombresMunicipios: List<String> = nombresMunicipios

    override fun getCount(): Int {
        return filteredNombresMunicipios.size
    }
    override fun getItem(position: Int): String? {
        return filteredNombresMunicipios[position]
    }
    fun getItemIdAtPosition(position: Int): String? {
        if (position < 0 || position >= filteredNombresMunicipios.size) {
            return null
        }
        val nombre = filteredNombresMunicipios[position]
        return municipios.find { it.nombre == nombre }?.idMunicipio
    }
    fun getTropicoAtPosition(position: Int): String? {
        if (position < 0 || position >= filteredNombresMunicipios.size) {
            return null
        }
        val nombre = filteredNombresMunicipios[position]
        return municipios.find { it.nombre == nombre }?.tipoTropico
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_municipios, parent, false)
        val textView = view.findViewById<TextView>(R.id.TvMunicipios)
        textView.text = getItem(position)
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase()

                val filteredList = if (queryString.isNullOrEmpty()) {
                    nombresMunicipios
                } else {
                    nombresMunicipios.filter {
                        it.toLowerCase().contains(queryString)
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredNombresMunicipios = results?.values as List<String>? ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}