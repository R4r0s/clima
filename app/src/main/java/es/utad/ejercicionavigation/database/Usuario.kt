package es.utad.ejercicionavigation.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario (
    @PrimaryKey val usuario: String,
    val password: String,
    val nombre: String
)