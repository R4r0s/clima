package es.utad.ejercicionavigation.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Ciudad (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val nombre: String?

):Parcelable