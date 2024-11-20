package model

data class Categoria(
    val nombre: String
) {
    // Método para poder mostrar el nombre de la categoría en un ListView
    @Override
    override fun toString(): String {
        return nombre
    }
}