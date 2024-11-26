package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.model.FincaModel

class FincasAdapter(context: Context,
                    resource: Int,
                    private val fincas: List<FincaModel>
) : ArrayAdapter<String>(context, resource) {

    private val nombresFincas: List<String> = fincas.map { it.nombre }
    private var filteredNombresFincas: List<String> = nombresFincas

    override fun getCount(): Int {
        return filteredNombresFincas.size
    }
    override fun getItem(position: Int): String? {
        return filteredNombresFincas[position]
    }
    fun getItemIdAtPosition(position: Int): String? {
        if (position < 0 || position >= filteredNombresFincas.size) {
            return null
        }
        val nombre = filteredNombresFincas[position]
        return fincas.find { it.nombre == nombre }?.idFinca
    }
    fun getAreaTotalAtPosition(position: Int): String {
        if (position < 0 || position >= filteredNombresFincas.size) {
            return ""
        }
        val nombre = filteredNombresFincas[position]
        return fincas.find { it.nombre == nombre }!!.areaTotal
    }

    fun getPeriodoRecuperacionAtPosition(position: Int): String {
        if (position < 0 || position >= filteredNombresFincas.size) {
            return ""
        }
        val nombre = filteredNombresFincas[position]
        return fincas.find { it.nombre == nombre }?.pasturas?.recuperacion.toString()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_fincas, parent, false)
        val textView = view.findViewById<TextView>(R.id.tvFinca)
        textView.text = getItem(position)
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase()

                val filteredList = if (queryString.isNullOrEmpty()) {
                    nombresFincas
                } else {
                    nombresFincas.filter {
                        it.toLowerCase().contains(queryString)
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredNombresFincas = results?.values as List<String>? ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}