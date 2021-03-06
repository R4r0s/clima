package es.utad.ejercicionavigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v =  inflater.inflate(R.layout.fragment_home, container, false)

        var buttonLogin = v.findViewById<Button>(R.id.buttonLogin)
        var buttonRegistro = v.findViewById<Button>(R.id.buttonRegistro)

        val sharedPreferences = activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)


        var userName = sharedPreferences?.getString("user", "null")
        var pass = sharedPreferences?.getString("pass", "null")

        if (userName == "null" || pass == "null"){

        }else{
           findNavController().navigate(R.id.action_homeFragment_to_listFragment)

        }

        buttonLogin.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        buttonRegistro.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }

        return v
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply { }
    }
}