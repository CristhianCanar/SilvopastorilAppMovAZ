package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.model.DepartamentoModel

class DepartamentosAdapter( context: Context,
                            resource: Int,
                            private val departamentos: List<DepartamentoModel>
) : ArrayAdapter<String>(context, resource) {

    private var nombresDepartamentos: List<String> = departamentos.map { it.nombre }
    private var filteredNombresDepartamentos: List<String> = nombresDepartamentos

    override fun getCount(): Int {
        return filteredNombresDepartamentos.size
    }

    override fun getItem(position: Int): String? {
        return filteredNombresDepartamentos[position]
    }

    fun getItemIdAtPosition(position: Int): String? {
        if (position < 0 || position >= filteredNombresDepartamentos.size) {
            return null
        }
        val nombre = filteredNombresDepartamentos[position]
        return departamentos.find { it.nombre == nombre }?.idDepartamento
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_departamentos, parent, false)
        val textView = view.findViewById<TextView>(R.id.TvDepartamentos)
        textView.text = getItem(position)
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase()

                val filteredList = if (queryString.isNullOrEmpty()) {
                    nombresDepartamentos
                } else {
                    nombresDepartamentos.filter {
                        it.toLowerCase().contains(queryString)
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredNombresDepartamentos = results?.values as List<String>? ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}