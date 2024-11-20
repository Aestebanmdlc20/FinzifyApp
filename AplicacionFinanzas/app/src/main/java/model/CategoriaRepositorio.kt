package model

// Objeto singleton CategoriaRepositorio que actúa como un repositorio de categorías
object CategoriaRepositorio {
    // Lista mutable de categorías inicializada con algunas categorías predeterminadas
    private val categorias = mutableListOf<Categoria>(
        Categoria("Ocio 🎉"),
        Categoria("Transporte 🚗"),
        Categoria("Salud 🏥"),
        Categoria("Otros 🗽")
    )

    // Función para obtener la lista de categorías
    fun obtenerCategorias(): List<Categoria> {
        return categorias // Retorna la lista de categorías
    }
}