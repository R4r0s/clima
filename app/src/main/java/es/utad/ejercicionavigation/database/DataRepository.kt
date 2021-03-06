package es.utad.ejercicionavigation.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val ciudadDao: CiudadDao? = AppDatabase.getInstance(context)?.peliculaDao()
    private val usuarioDao: UsuarioDao? = AppDatabase.getInstance(context)?.usuarioDao()

    fun existeUsuario(usuario:String, password:String): Boolean{
        if (usuarioDao!=null){
            var datos = ArrayList<String>()
            datos.add(usuario)
            datos.add(password)
            return (ExisteUsuarioAsyncTask(usuarioDao).execute(datos).get()==1)
        }
        return false
    }

    fun countUsuario(): Int{
        if (usuarioDao != null){
            return CountUsuarioAsyncTask(usuarioDao).execute().get()
        }
        return 0
    }

    fun insert(usuario: Usuario):Int {
        if (ciudadDao != null){
            return InsertUsuarioAsyncTask(usuarioDao).execute(usuario).get()
        }
        return -1
    }

    private class ExisteUsuarioAsyncTask(private val usuarioDao: UsuarioDao) : AsyncTask<List<String>, Void, Int>() {
        override fun doInBackground(vararg datos: List<String>): Int? {
            try {
                return usuarioDao.countUsuarioByUsuarioPassword(datos[0][0], datos[0][1])
            }
            catch (exception: Exception){
                return 0
            }
        }
    }

    private class CountUsuarioAsyncTask(private val usuarioDao: UsuarioDao) : AsyncTask<Void, Void, Int>() {

        override fun doInBackground(vararg p0: Void?): Int? {
            try {
                return usuarioDao.countUsuario()
            }
            catch (exception: Exception){
                return 0
            }
        }
    }

    private class InsertUsuarioAsyncTask(private val usuarioDao: UsuarioDao?) : AsyncTask<Usuario, Void, Int>() {
        override fun doInBackground(vararg usuario: Usuario): Int? {
            try {
                if (usuarioDao != null) {
                    usuarioDao.insert(usuario[0])
                }
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    /******************************Ciudades **********************************/
    fun insert(ciudad: Ciudad):Int {
        if (ciudadDao != null){
            return InsertCiudadAsyncTask(ciudadDao).execute(ciudad).get()
        }
        return -1
    }

    fun getCiudad():List<Ciudad> {
        if (ciudadDao != null){
            return getCiudadAsyncTask(ciudadDao).execute().get()
        }
        return ArrayList<Ciudad>()
    }

    fun buscar(nombre: String): List<Ciudad> {
        if (ciudadDao != null){
            return GetCiudadByNombreAsyncTask(ciudadDao).execute(nombre).get()
        }
        return ArrayList<Ciudad>()
    }

    fun delete(ciudad: Ciudad):Int {
        if (ciudadDao != null){
            return DeleteCiudadAsyncTask(ciudadDao).execute(ciudad).get()
        }
        return -1
    }


    private class InsertCiudadAsyncTask(private val ciudadDao: CiudadDao) : AsyncTask<Ciudad, Void, Int>() {
        override fun doInBackground(vararg ciudads: Ciudad): Int? {
            try {
                ciudadDao.insert(ciudads[0])
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class GetCiudadByNombreAsyncTask(private val ciudadDao: CiudadDao) : AsyncTask<String, Void, List<Ciudad>>() {
        override fun doInBackground(vararg ciudads: String): List<Ciudad> {
            try {
                var ciudad = ciudadDao.getCiudadByNombre(ciudads[0])
                return ciudad
            }
            catch (exception: Exception){
                return ArrayList<Ciudad>()
            }
        }
    }

    private class DeleteCiudadAsyncTask(private val ciudadDao: CiudadDao) : AsyncTask<Ciudad, Void, Int>() {
        override fun doInBackground(vararg ciudads: Ciudad): Int? {
            try {
                ciudadDao.delete(ciudads[0])
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }



    private class getCiudadAsyncTask(private val ciudadDao: CiudadDao) : AsyncTask<String, Void, List<Ciudad>>() {
        override fun doInBackground(vararg tipo: String): List<Ciudad> {
            try {
                var ciudad = ciudadDao.getCiudad()
                return ciudad
            }
            catch (exception: Exception){
                return ArrayList<Ciudad>()
            }
        }
    }

}