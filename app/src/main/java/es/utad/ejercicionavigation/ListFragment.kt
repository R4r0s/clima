package es.utad.ejercicionavigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.utad.ejercicionavigation.adapters.CiudadAdapter
import es.utad.ejercicionavigation.database.DataRepository
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    lateinit var recyclerViewLista: RecyclerView
    private lateinit var insertar: Button
    private lateinit var borrar: Button
    private lateinit var buscar: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val v =  inflater.inflate(R.layout.fragment_list, container, false)
        val CerrarSesion = v.findViewById<Button>(R.id.buttonCerrarSesion)
        val sharedPreferences = activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        insertar = v.findViewById(R.id.buttonInsertar)
        borrar = v.findViewById(R.id.buttonBorrar)
        buscar = v.findViewById(R.id.buttonBuscar)
        recyclerViewLista = v.findViewById(R.id.recyclerviewlista)

        CerrarSesion.setOnClickListener(View.OnClickListener {
            editor?.putString("user", "null")
            editor?.putString("pass", "null")
            editor?.commit()
            findNavController().navigate(R.id.action_listFragment_to_homeFragment)
        })

        insertar.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_insertarFragment)
        })

        borrar.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_eliminarFragment2)
        })

        buscar.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_buscarFragment)
        })

        rellenarListaCiudades()
        return v
    }

    fun rellenarListaCiudades() {
        var dataRepository = DataRepository(requireContext())
        var peliculas = dataRepository.getCiudad()
        val adapter = CiudadAdapter(peliculas)

        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ListFragment().apply { }
    }
}