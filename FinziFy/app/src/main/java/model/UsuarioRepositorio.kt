package model

object UsuarioRepositorio {
    private val usuarios = mutableListOf<Usuario>(
        Usuario("Alejandro Esteban", "alejandrodev@gmail.com",1500.00),
        Usuario("María", "mariaprueba@gmail.com",200.00)
    )

    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun obtenerUsuarioPorCorreo(correo: String): Usuario? {
        return usuarios.find { it.correo == correo }
    }
}