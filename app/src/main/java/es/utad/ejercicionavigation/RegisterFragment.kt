package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import es.utad.ejercicionavigation.database.DataRepository
import es.utad.ejercicionavigation.database.Usuario
import java.math.BigInteger
import java.security.MessageDigest


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextNombre: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_register, container, false)

        editTextUsuario = v.findViewById<EditText>(R.id.editTextRegisterUsuario)
        editTextPassword = v.findViewById<EditText>(R.id.editTextRegisterPassword)
        editTextNombre = v.findViewById<EditText>(R.id.editTextRegisterNombre)

        val buttonRegisterOk = v.findViewById<Button>(R.id.buttonRegisterOk)
        buttonRegisterOk.setOnClickListener {
            procesarRegistro()
        }

        return v
    }


    private fun procesarRegistro(){
        val dataRepository = DataRepository(requireContext())
        if (dataRepository.insert(Usuario(editTextUsuario.text.toString(), md5(editTextPassword.text.toString()), editTextNombre.text.toString())) == -1){
            Toast.makeText(requireContext(), "Usuario repetido", Toast.LENGTH_LONG).show()
        }
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {  }
    }
}