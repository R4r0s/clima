package es.utad.ejercicionavigation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import es.utad.ejercicionavigation.ListFragmentDirections
import es.utad.ejercicionavigation.R
import es.utad.ejercicionavigation.database.Ciudad

class CiudadAdapter(var ciudad: List<Ciudad>) : RecyclerView.Adapter<CiudadAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pelicula_item, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CiudadAdapter.ViewHolder, position: Int) {
        holder.bindItems(ciudad[position])
        holder.itemView.setOnClickListener {view->


            val bundleCiudad = bundleOf(Pair("ciudad", ciudad[position]))
            val action = ListFragmentDirections.actionListFragmentToFichaFragment(bundleCiudad)
            view.findNavController().navigate(action)

        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ciudad.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(ciudades: Ciudad) {
            val textViewNombre = itemView.findViewById<TextView>(R.id.textViewTitulo)
            textViewNombre.text = ciudades.nombre
        }
    }
}