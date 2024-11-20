package model // Define el paquete al que pertenece este código

object UsuarioRepositorio { // Define un objeto llamado UsuarioRepositorio, que actúa como un repositorio de usuarios
    // Una lista mutable para almacenar los usuarios. Inicializada con dos usuarios de ejemplo.
    private val usuarios = mutableListOf<Usuario>(
        Usuario("Alejandro Esteban", "alejandrodev@gmail.com",1500.00),
        Usuario("María", "mariaprueba@gmail.com",200.00)
    )

    // Función para agregar un nuevo usuario a la lista de usuarios.
    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    // Función para buscar un usuario por su correo electrónico.
    // Devuelve el usuario si se encuentra, o null si no se encuentra.
    fun obtenerUsuarioPorCorreo(correo: String): Usuario? {
        return usuarios.find { it.correo == correo }
    }
}