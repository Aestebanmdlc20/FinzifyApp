package model

import android.os.Bundle

data class Gasto(
    val nombre: String,
    val descripcion: String,
    val monto: Double,
    val categoria: Categoria
) {
    // Método para convertir un Gasto a un Bundle
    fun toBundle(): Bundle {
        return Bundle().apply {
            putString("nombre", nombre)
            putString("descripcion", descripcion)
            putDouble("monto", monto)
            putString("categoria", categoria.nombre)
        }
    }

    @Override
    override fun toString(): String {
        return nombre
    }

    // Método para crear un Gasto desde un Bundle
    companion object {
        fun fromBundle(bundle: Bundle): Gasto {
            return Gasto(
                nombre = bundle.getString("nombre") ?: "",
                descripcion = bundle.getString("descripcion") ?: "",
                monto = bundle.getDouble("monto"),
                categoria = Categoria(bundle.getString("categoria") ?: "Otros")
            )
        }
    }
}