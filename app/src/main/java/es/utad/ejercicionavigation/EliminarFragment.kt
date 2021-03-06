package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import es.utad.ejercicionavigation.database.Ciudad
import es.utad.ejercicionavigation.database.DataRepository



class EliminarFragment : Fragment() {
    private lateinit var botonEliminar: Button
    private lateinit var editTextCiudad: EditText
    private lateinit var nombre: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_eliminar, container, false)
        editTextCiudad = v.findViewById(R.id.editTextTextCiudadEliminar)
        botonEliminar = v.findViewById(R.id.buttonBorrarFragmento)

        botonEliminar.setOnClickListener {
            val dataRepository = DataRepository(requireContext())
            nombre = editTextCiudad.text.toString()
            val ciudad = dataRepository.buscar(nombre)
            dataRepository.delete(Ciudad(ciudad[0].id, nombre))
        }

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EliminarFragment().apply {}
    }
}