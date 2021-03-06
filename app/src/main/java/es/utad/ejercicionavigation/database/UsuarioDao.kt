package es.utad.ejercicionavigation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("SELECT count(*) FROM usuario where usuario = :usuarioParamero and password = :passwordParametro")
    fun countUsuarioByUsuarioPassword(usuarioParamero:String, passwordParametro: String): Int?

    @Query("SELECT count(*) FROM usuario")
    fun countUsuario(): Int?

    @Insert
    fun insert(usuario: Usuario)
}