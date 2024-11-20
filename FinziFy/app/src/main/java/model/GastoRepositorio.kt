package model

// Objeto singleton GastoRepositorio que actúa como un repositorio de gastos
object GastoRepositorio {
    // Lista mutable de gastos
    private val gastos = mutableListOf<Gasto>()

    // Función para agregar un gasto a la lista
    fun agregarGasto(gasto: Gasto) {
        gastos.add(gasto) // Añade el gasto a la lista
    }

    // Función para obtener los gastos filtrados por categoría
    fun obtenerGastosPorCategoria(categoria: Categoria): List<Gasto> {
        return gastos.filter { it.categoria == categoria } // Filtra y retorna los gastos que pertenecen a la categoría especificada
    }
}