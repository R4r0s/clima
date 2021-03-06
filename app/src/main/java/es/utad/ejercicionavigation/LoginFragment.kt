package es.utad.ejercicionavigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import es.utad.ejercicionavigation.database.DataRepository
import kotlinx.android.synthetic.main.fragment_login.*
import java.math.BigInteger
import java.security.MessageDigest


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_login, container, false)
        editTextUsuario = v.findViewById(R.id.editTextUsuario)
        editTextPassword = v.findViewById(R.id.editTextPassword)

        val buttonLogin = v.findViewById<Button>(R.id.buttonLoginOk)

        val sharedPreferences = activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()


        buttonLogin.setOnClickListener {
            editor?.putString("user", editTextUsuario.text.toString())
            editor?.putString("pass", editTextPassword.text.toString())
            editor?.commit()
            procesarLogin()
        }

        return v
    }

    private fun procesarLogin() {
        val dataRepository = DataRepository(requireContext())

        if (dataRepository.countUsuario() == 0) {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        } else {
            if (dataRepository.existeUsuario(editTextUsuario.text.toString(), md5(editTextPassword.text.toString()))) {
                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Datos incorrectos", Toast.LENGTH_LONG)
            }
        }

    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply { }
        }
    }
