package model

// Clase data Usuario que representa a un usuario con nombre, correo y saldo
data class Usuario(
    var nombre: String, // Propiedad nombre de tipo String
    var correo: String,
    var saldo: Double// Propiedad correo de tipo String
) {

    // Función para añadir saldo al usuario
    fun addSaldo(email: String, saldo: Double) {
        this.saldo += saldo // Incrementa el saldo actual con el valor proporcionado
    }

    // Función para restar saldo al usuario
    fun restarSaldo(email: String, saldo: Double) {
        this.saldo -= saldo // Decrementa el saldo actual con el valor proporcionado
    }
}