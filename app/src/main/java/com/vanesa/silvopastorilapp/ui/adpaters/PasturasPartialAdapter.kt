package com.vanesa.silvopastorilapp.ui.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.vanesa.silvopastorilapp.R
import com.vanesa.silvopastorilapp.data.model.PasturaPartialModel

class PasturasPartialAdapter(context: Context,
                             resource: Int,
                             private val pasturas: List<PasturaPartialModel>
) : ArrayAdapter<String>(context, resource) {

    private var nombresPasturas: List<String> = pasturas.map { it.nombre }
    private var filteredNombresPasturas: List<String> = nombresPasturas

    override fun getCount(): Int {
        return filteredNombresPasturas.size
    }

    override fun getItem(position: Int): String? {
        return filteredNombresPasturas[position]
    }

    fun getItemIdAtPosition(position: Int): String? {
        if (position < 0 || position >= filteredNombresPasturas.size) {
            return null
        }
        val nombre = filteredNombresPasturas[position]
        return pasturas.find { it.nombre == nombre }?.idPastura
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_pasturas_partial, parent, false)
        val textView = view.findViewById<TextView>(R.id.TvPasturasPartial)
        textView.text = getItem(position)
        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase()

                val filteredList = if (queryString.isNullOrEmpty()) {
                    nombresPasturas
                } else {
                    nombresPasturas.filter {
                        it.toLowerCase().contains(queryString)
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredNombresPasturas = results?.values as List<String>? ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}