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


class InsertarFragment : Fragment() {
    private lateinit var insertar : Button
    private lateinit var ciudadNombre : EditText
    private lateinit var nombre: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_insertar, container, false)
        insertar = v.findViewById(R.id.buttonInsertarFragmento)
        ciudadNombre = v.findViewById(R.id.editTextTextCiudad)

        insertar.setOnClickListener(View.OnClickListener{
            val dataRepository = DataRepository(requireContext())
            nombre = ciudadNombre.text.toString()
            dataRepository.insert((Ciudad(0, nombre)))
        })
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InsertarFragment().apply {}
            }
    }
