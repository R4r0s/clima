package es.utad.ejercicionavigation.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CiudadDao {



    @Query("SELECT * FROM ciudad")
    fun getCiudad(): List<Ciudad>

    @Query("SELECT * FROM ciudad where nombre = :nombre")
    fun getCiudadByNombre(nombre: String): List<Ciudad>

    @Insert
    fun insert(ciudad: Ciudad)

    @Delete
    fun delete(ciudad: Ciudad)
}