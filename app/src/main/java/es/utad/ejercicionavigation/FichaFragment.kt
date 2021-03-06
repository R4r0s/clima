package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import es.utad.ejercicionavigation.database.Ciudad
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.utad.ejercicionavigation.utils.Datos
import org.json.JSONException


/**
 * A simple [Fragment] subclass.
 * Use the [FichaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FichaFragment : Fragment() {

    lateinit var textViewNombre: TextView
    lateinit var textViewClima: TextView
    lateinit var textViewTemp: TextView
    lateinit var textViewHumeadad: TextView
    private var clima = "Algo salio mal"
    private var temp = 0.0
    private var humedad = 0
    private var url = "https://api.openweathermap.org/data/2.5/weather?q="
    private var key = "&lang=es&units=metric&appid=e724b640727ed058a7b4e2b99b56623c"
    private var lista = MutableLiveData<ArrayList<Datos>>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_ficha, container, false)
        textViewNombre = v.findViewById(R.id.textViewFichaCiudad)

        textViewClima = v.findViewById(R.id.textViewFichaClima)
        textViewTemp = v.findViewById(R.id.textViewFichaTemp)
        textViewHumeadad = v.findViewById(R.id.textViewFichaHumedad)

        val args: FichaFragmentArgs by navArgs()

        val ciudad = args.idPelicula["ciudad"] as Ciudad

        textViewNombre.text = "Nombre ciudad: " + ciudad.nombre
        rellenarAPI(ciudad.nombre.toString())
        lista.observe(viewLifecycleOwner, Observer {
            textViewClima.text = "El tiempo atmosferico es: " + clima
            textViewTemp.text = "La temperatura es: " + temp.toString() + "ºC"
            textViewHumeadad.text = "La humedad es: " + humedad.toString() + "%"
        })
        return v
    }


    fun rellenarAPI(ciudad: String) {
        var urlFinal = url + ciudad + key

        val requestQueue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.GET, urlFinal, null,
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

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FichaFragment().apply { }
    }
}