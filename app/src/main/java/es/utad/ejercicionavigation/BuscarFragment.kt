package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.utad.ejercicionavigation.database.DataRepository
import es.utad.ejercicionavigation.utils.Datos
import kotlinx.android.synthetic.main.fragment_buscar.*
import org.json.JSONException

class BuscarFragment : Fragment() {
    private lateinit var editTextBuscar: EditText
    private lateinit var buttonBuscarFragment: Button
    lateinit var textViewClima: TextView
    lateinit var textViewTemp: TextView
    lateinit var textViewHumeadad: TextView
    private lateinit var textViewNombre: TextView
    private lateinit var nombre: String
    private var url = "https://api.openweathermap.org/data/2.5/weather?q="
    private var key = "&lang=es&units=metric&appid=e724b640727ed058a7b4e2b99b56623c"
    private var lista = MutableLiveData<ArrayList<Datos>>()
    private var clima = "Algo salio mal"
    private var temp = 0.0
    private var humedad = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_buscar, container, false)
        editTextBuscar = v.findViewById(R.id.editTextTextCiudadEditar)
        buttonBuscarFragment = v.findViewById(R.id.buttonBuscarFragment)
        textViewNombre = v.findViewById(R.id.textViewNombreRellenar)
        textViewClima = v.findViewById(R.id.textViewClimaRellenar)
        textViewTemp = v.findViewById(R.id.textViewTemperaturaRellenar)
        textViewHumeadad = v.findViewById(R.id.textViewHumedadRellenar)
        buttonBuscarFragment.setOnClickListener {
            val dataRepository = DataRepository(requireContext())
            nombre = editTextBuscar.text.toString()
            var ciudad = dataRepository.buscar(nombre)
            textViewNombre.text = ciudad[0].nombre
            rellenarAPI(ciudad[0].nombre.toString())
        }
        lista.observe(viewLifecycleOwner, Observer {
            textViewClima.text = clima
            textViewTemp.text = temp.toString() + "ºC"
            textViewHumeadad.text = humedad.toString() + "%"
        })


        return v
    }

    fun rellenarAPI(ciudad: String) {
        var urlFinal = url + ciudad + key

        val requestQueue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET, urlFinal, null,
            Response.Listener { response ->
                try {
                    var listaDatos = ArrayList<Datos>()

                    val weather = response.getJSONArray("weather")
                    for (i in 0 until weather.length()) {
                        val weath = weather.getJSONObject(i)
                        clima = weath.getString("description")
                    }
                    val main = response.getJSONObject("main")
                    temp = main.getDouble("temp")
                    humedad = main.getInt("humidity")
                    listaDatos.add(Datos(clima, temp, humedad))
                    lista.value = listaDatos
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            })
        requestQueue.add(request)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BuscarFragment().apply {}
    }
}